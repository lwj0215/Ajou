# Simple-IPC

Simple-IPC is a Linux kernel module assignment.

## Directory Layout

```text
simple-ipc/
├── apps/
│   ├── server.c
│   └── client.c
├── include/
│   ├── abi.h
│   └── kv.h
├── kernel/
│   ├── Makefile
│   └── core.c
├── docs/
│   └── README.md
└── Makefile
```

## Installation

Build the project:

```bash
make
```

Load the kernel module:

```bash
sudo insmod .build/simipc.ko
```

Verify that the module is loaded:

```bash
sudo dmesg | tail
```

Unload the kernel module:

```bash
sudo rmmod simipc
```

Clean build artifacts:

```bash
make clean
```


# Notes for me


## Functions
```c
unsigned long copy_from_user(void *to, const void __user *from, unsigned long n)
```
- user-space to kernel
- to: kernel pointer
- from: user pointer
- n: size of bytes
- return:
    - 0: succeed
    - size of bytes: failed

```c
unsigned long copy_to_user(void __user *to, const void *from, unsigned long n);
```
- kernel to user
- to: user pointer
- from: kernel pointer
 
```c
DECLARE_WAIT_QUEUE_HEAD(name);
wait_queue_head_t name;
wait_event_interruptible(wait_queue_head_t q, condition);
wake_up_interruptible(wait_queue_head_t *q)
```
- q: queue for wait

## MACROS
```c
_IOW(type, nr, data_type)
_IOR(type, nr, data_type)
```
- IOW: I/O Write, user to kernel
- IOR: I/O Read, kernel to user
- type: magic number
- nr: command number

# Appendix
https://docs.oracle.com/cd/E19683-01/816-5042/svipc-23310/index.html
