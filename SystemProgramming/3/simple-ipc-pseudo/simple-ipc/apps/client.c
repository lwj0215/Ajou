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
    struct ipc ipc_msg;

    if (argc < 4) {
        usage(argv[0]);
        return 1;
    }
    // TODO: Implement the client request/reply flow.
    int op = parse_op(argv[2]);

    memset(&payload, 0, sizeof(payload));
    memset(&ipc_msg, 0, sizeof(payload));

    strncpy(ipc_msg.endpoint, argv[1], SIMPLE_IPC_NAME_MAX-1);
    strncpy(ipc_msg.endpoint, argv[1], SIMPLE_IPC_NAME_MAX-1);
    strncpy(ipc_msg.endpoint, argv[1], SIMPLE_IPC_NAME_MAX-1);

    payload->op = op;
    payload->status = 0;

    strcpy()
    payload->key = *argv[3];
    payload->value = argv[4];

    ipc_msg.endpoint = ;
    ipc_msg.msg_id = ;
    ipc_msg.payload_len;
    ipc_msg.payload = payload;

    ioctl()

    return 0;
}
