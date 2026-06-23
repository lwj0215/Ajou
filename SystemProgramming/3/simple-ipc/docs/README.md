# Simple-IPC

Simple-IPC is a Linux kernel module assignment.
No message-queue
Supporting single kernel module-server connection
Check expected inputs below

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

## Environments
- Ubuntu 24.04.4 LTS
- gcc
- make
- VMware® Workstation Pro 25H2 (2cores, 4GB, 20GB)

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

# Caution
## Expected inputs
```bash
./server {ENDPOINT}
./client {ENDPOINT} PUT {KEY} {VALUE}
./client {ENDPOINT} GET {KEY}
```
There should be problems for unexpected inputs

## Functions not on code
- no function for multiple endpoints
- no exception for duplicated key
- no function for duplicated value on same key
- no exception for table overflow
- no exception for not-found key on client
- no exception for not registered endpoint
- no function for matching message-id
- no function for matching endpoint with server


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
- 0 in condition, make proc absolutely sleep, no wake_up

## MACROS
```c
_IOW(type, nr, data_type)
_IOR(type, nr, data_type)
```
- IOW: I/O Write, user to kernel
- IOR: I/O Read, kernel to user
- type: magic number
- nr: command number
