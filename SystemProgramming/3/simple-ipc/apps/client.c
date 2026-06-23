#include <errno.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/ioctl.h>
#include <unistd.h>

#include "../include/abi.h"
#include "../include/kv.h"

static void usage(const char *prog) {
    fprintf(stderr, "Usage:\n");
    fprintf(stderr, "  %s <endpoint> PUT <key> <value>\n", prog);
    fprintf(stderr, "  %s <endpoint> GET <key>\n", prog);
}

static int parse_op(const char *op) {
    if (strcmp(op, "PUT") == 0)
        return KV_OP_PUT;

    if (strcmp(op, "GET") == 0)
        return KV_OP_GET;

    return -EINVAL;
}

int main(int argc, char **argv) {
    int fd;
    fd = open(SIMPLE_IPC_DEVICE_PATH, O_RDWR);

    struct kv_payload payload;
    struct ipc msg;
    struct ipc msg_recv;
    memset(&payload, 0, sizeof(payload));
    memset(&msg, 0, sizeof(msg));
    memset(&msg_recv, 0, sizeof(msg_recv));

    if (fd < 0) {
        perror("open");
        return EXIT_FAILURE;
    }
    if (argc < 4) {
        usage(argv[0]);
        return 1;
    }

    // make kv_payload struct
    int op = parse_op(argv[2]);
    payload.op = op;
    payload.status = 0;
    strncpy(payload.key, argv[3], KV_KEY_MAX-1);
    if(op == KV_OP_PUT)
        strncpy(payload.value, argv[4], KV_VALUE_MAX-1);

    // make ipc struct
    strncpy(msg.endpoint, argv[1], SIMPLE_IPC_NAME_MAX-1);
    msg.payload_len = sizeof(payload);
    memcpy(msg.payload, &payload, sizeof(payload));

    // send message
    if(ioctl(fd, SIMPLE_IPC_IOC_SEND_REQUEST, &msg) == -1) {
        perror("IOCTL ERR");
        close(fd);
        return EXIT_FAILURE;
    }

    /*
    Receive reply
    GET: value in payload
    PUT: empty payload
    */
    if(ioctl(fd, SIMPLE_IPC_IOC_RECV_REPLY, &msg_recv) == -1) {
        perror("IOCTL ERR");
        close(fd);
        return EXIT_FAILURE;
    }

    printf("%s\n", msg_recv.payload);
    close(fd);
    return EXIT_SUCCESS;
}
