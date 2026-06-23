#include <linux/fs.h>
#include <linux/init.h>
#include <linux/miscdevice.h>
#include <linux/module.h>
#include <linux/uaccess.h>
#include <linux/wait.h>

#include "../include/abi.h"

MODULE_LICENSE("GPL");
MODULE_AUTHOR("");
MODULE_DESCRIPTION("");
MODULE_VERSION("");

#define DRIVER_NAME "simipc"

static DECLARE_WAIT_QUEUE_HEAD(server_waitq);
static DECLARE_WAIT_QUEUE_HEAD(client_waitq);
static char server_endpoint[SIMPLE_IPC_NAME_MAX];
static struct ipc msg_recv;
static int flag; // 0 : when client <-> core, 1 : when server <-> core

static long simple_ipc_ioctl(struct file *file, unsigned int cmd, unsigned long arg) {
    switch (cmd) {
    // client -> core
    case SIMPLE_IPC_IOC_SEND_REQUEST: {
        if(copy_from_user(&msg_recv, (char __user *)arg, sizeof(msg_recv)))
            return -EFAULT;
        pr_info("Recv req-msg from client\n");
        flag = 1;
        wake_up_interruptible(&server_waitq);
        wait_event_interruptible(client_waitq, flag == 0);
        return 0;
    }
    // core -> server
    case SIMPLE_IPC_IOC_RECV_REQUEST: {
        wait_event_interruptible(server_waitq, flag == 1);
        if(copy_to_user((char __user *)arg, &msg_recv, sizeof(msg_recv)))
            return -EFAULT;
        pr_info("Send req-msg to server\n");
        return 0;
    }
    // server -> core
    case SIMPLE_IPC_IOC_SEND_REPLY: {
        if(copy_from_user(&msg_recv, (char __user *)arg, sizeof(msg_recv)))
            return -EFAULT;
        pr_info("Recv res-msg from server\n");
        flag = 0;
        wake_up_interruptible(&client_waitq);
        wait_event_interruptible(server_waitq, flag == 1);
        return 0;
    }
    // core -> client
    case SIMPLE_IPC_IOC_RECV_REPLY: {
        if(copy_to_user((char __user *)arg, &msg_recv, sizeof(msg_recv)))
            return -EFAULT;
        pr_info("Send res-msg to client\n");
        return 0;
    }

    case SIMPLE_IPC_IOC_BIND_SERVER: {
        if(copy_from_user(&msg_recv, (char __user *)arg, sizeof(msg_recv)))
            return -EFAULT;
        pr_info("BIND %s\n", msg_recv.endpoint);
        strncpy(server_endpoint, msg_recv.endpoint, SIMPLE_IPC_NAME_MAX-1);
        return 0;
    }
    case SIMPLE_IPC_IOC_UNBIND_SERVER: {
        if(copy_from_user(&msg_recv, (char __user *)arg, sizeof(msg_recv)))
            return -EFAULT;
        pr_info("UNBIND %s\n", msg_recv.endpoint);
        return 0;
    }

    default:
        return -ENOTTY;
    }
}

static const struct file_operations simple_ipc_fops = {
    .owner = THIS_MODULE,
    .unlocked_ioctl = simple_ipc_ioctl,
#ifdef CONFIG_COMPAT
    .compat_ioctl = simple_ipc_ioctl,
#endif
};

static struct miscdevice simple_ipc_dev = {
    .minor = MISC_DYNAMIC_MINOR,
    .name = DRIVER_NAME,
    .fops = &simple_ipc_fops,
    .mode = 0666,
};

static int __init simple_ipc_init(void) {
    int ret;

    ret = misc_register(&simple_ipc_dev);
    if (ret)
        return ret;

    pr_info("simple_ipc: module loaded\n");
    return 0;
}

static void __exit simple_ipc_exit(void) {
    misc_deregister(&simple_ipc_dev);
    pr_info("simple_ipc: module unloaded\n");
}

module_init(simple_ipc_init);
module_exit(simple_ipc_exit);
