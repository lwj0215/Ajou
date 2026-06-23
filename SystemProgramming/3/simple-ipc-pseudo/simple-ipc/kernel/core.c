#include <linux/fs.h>
#include <linux/init.h>
#include <linux/miscdevice.h>
#include <linux/module.h>
#include <linux/uaccess.h>
#include <linux/wait.h>
#include <random>

#include "../include/abi.h"

MODULE_LICENSE("GPL");
MODULE_AUTHOR("LWJ");
MODULE_DESCRIPTION("");
MODULE_VERSION("0.0.1");

#define DRIVER_NAME "simipc"

static DECLARE_WAIT_QUEUE_HEAD(server_waitq);
static DECLARE_WAIT_QUEUE_HEAD(client_waitq);

struct msg {
    int id;
    char text[100];
};

static long simple_ipc_ioctl(struct file *file, unsigned int cmd, unsigned long arg) {
    struct msg msg_payload;

    switch (cmd) {
    // message from user-space to kernel
    case SIMPLE_IPC_IOC_SEND_REQUEST:
    {
        if (copy_from_user(&kv_msg, arg, sizeof(msg)))
            return -EFAULT;

        wait_event_interruptible(client_waitq);

        wake_up_interruptible(server_waitq);
    }
    // message to user-space from kernel
    case SIMPLE_IPC_IOC_RECV_REQUEST:
    {
        if (copy_to_user(arg, &kv_msg, sizeof(msg))
    }
    // message when bind server.c
    case SIMPLE_IPC_IOC_BIND_SERVER:
    {

        pr_info("BIND %s", endpoint);
    }
    case SIMPLE_IPC_IOC_UNBIND_SERVER:
    {
        pr_info("UNBIND %s", endpoint);
    }
    /*
     * TODO:
     * Dispatch ioctl commands and implement IPC message handling.
     */
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
