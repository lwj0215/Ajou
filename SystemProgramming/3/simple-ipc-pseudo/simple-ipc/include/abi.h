#ifndef SIMPLE_IPC_ABI_H
#define SIMPLE_IPC_ABI_H

/*
 * Simple-IPC ABI
 *
 * This header defines the user/kernel ABI shared by the kernel module,
 * server process, and client process. The kernel module must treat payload
 * bytes as opaque data and must not interpret application-level semantics.
 */

#ifdef __KERNEL__
    #include <linux/types.h>
    #include <linux/ioctl.h>
    typedef   __u64     u64;
    typedef   __u32     u32;
    typedef   __u8      u8;
#else
    #include <stdint.h>
    #include <sys/ioctl.h>
    typedef   uint64_t  u64;
    typedef   uint32_t  u32;
    typedef   uint8_t   u8;
#endif


#define SIMPLE_IPC_DEVICE_PATH "/dev/simipc"
#define SIMPLE_IPC_MAGIC       's'
#define SIMPLE_IPC_NAME_MAX    32
#define SIMPLE_IPC_PAYLOAD_MAX 128

struct ipc {
    char endpoint[SIMPLE_IPC_NAME_MAX];
    u32 msg_id;
    u32 payload_len;
    u8 payload[SIMPLE_IPC_PAYLOAD_MAX];
};

#define SIMPLE_IPC_IOC_BIND_SERVER    _IOW(SIMPLE_IPC_MAGIC, 1, char[SIMPLE_IPC_NAME_MAX])
#define SIMPLE_IPC_IOC_UNBIND_SERVER  _IOW(SIMPLE_IPC_MAGIC, 2, char[SIMPLE_IPC_NAME_MAX])
#define SIMPLE_IPC_IOC_SEND_REQUEST   _IOW(SIMPLE_IPC_MAGIC, 3, struct ipc)
#define SIMPLE_IPC_IOC_RECV_REQUEST   _IOR(SIMPLE_IPC_MAGIC, 4, struct ipc)
#define SIMPLE_IPC_IOC_SEND_REPLY     _IOW(SIMPLE_IPC_MAGIC, 5, struct ipc)
#define SIMPLE_IPC_IOC_RECV_REPLY     _IOR(SIMPLE_IPC_MAGIC, 6, struct ipc)

#endif /* SIMPLE_IPC_ABI_H */
