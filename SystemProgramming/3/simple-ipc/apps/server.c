#include <errno.h>
#include <fcntl.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/ioctl.h>
#include <unistd.h>

#include "../include/abi.h"
#include "../include/kv.h"

#define KV_TABLE_MAX 32

struct kv_entry {
    int used;
    char key[KV_KEY_MAX];
    char value[KV_VALUE_MAX];
};

static struct kv_entry table[KV_TABLE_MAX];
static char endpoint[SIMPLE_IPC_NAME_MAX];
static int table_pointer = 0;
static int fd;
static volatile sig_atomic_t running = 1;

static void cleanup_server() {
    // Unbind the server before termination.
    ioctl(fd, SIMPLE_IPC_IOC_UNBIND_SERVER, endpoint);
    close(fd);
    fd = -1;
}

static void handle_signal(int signo) {
    (void)signo;
    // TODO: Implement signal action handler.
    running = 0;
}

int main(int argc, char **argv) {
    struct sigaction sa;
    struct kv_payload payload;
    struct kv_payload payload_recv;
    struct ipc msg;
    struct ipc msg_recv;

    fd = open(SIMPLE_IPC_DEVICE_PATH, O_RDWR);
    if (fd < 0) {
        perror("Failed to open device");
        return 1;
    }

    if (argc != 2) {
        fprintf(stderr, "Usage: %s <endpoint>\n", argv[0]);
        return 1;
    }
    strncpy(endpoint, argv[1], SIMPLE_IPC_NAME_MAX-1);
    strncpy(msg.endpoint, endpoint, SIMPLE_IPC_NAME_MAX-1);

    memset(&sa, 0, sizeof(sa));
    sa.sa_handler = handle_signal;
    sigemptyset(&sa.sa_mask);

    sigaction(SIGINT, &sa, NULL);
    sigaction(SIGTERM, &sa, NULL);

    atexit(cleanup_server);

    if(ioctl(fd, SIMPLE_IPC_IOC_BIND_SERVER, &msg) == -1) {
        perror("IOCTL BIND ERR");
        close(fd);
        return EXIT_FAILURE;
    }

    // server main loop.
    while(running) {
        memset(&payload, 0, sizeof(payload));
        memset(&payload_recv, 0, sizeof(payload_recv));
        memset(&msg, 0, sizeof(msg));
        memset(&msg_recv, 0, sizeof(msg_recv));
        // get message
        if(ioctl(fd, SIMPLE_IPC_IOC_RECV_REQUEST, &msg_recv) == -1) {
            perror("IOCTL ERR");
            return EXIT_FAILURE;
        }
        memcpy(&payload_recv, msg_recv.payload, sizeof(payload_recv));

        // PUT into kv table
        if(payload_recv.op == KV_OP_PUT) {
            if(table_pointer >= KV_TABLE_MAX) {
                printf("NOT ENOUGH SPACE\n");
                continue;
            }
            table[table_pointer].used = 1;
            strncpy(table[table_pointer].key, payload_recv.key, KV_KEY_MAX-1);
            strncpy(table[table_pointer].value, payload_recv.value, KV_VALUE_MAX-1);
            printf("PUT K: %s V: %s\n", table[table_pointer].key, table[table_pointer].value);
            table_pointer++;
        }

        int ch = 0;
        char value[KV_VALUE_MAX];
        msg.payload[0] = '\0';
        msg.msg_id = msg_recv.msg_id;
        /*
        GET, finding value
        Traverse all entries, O(n)
        */
        if(payload_recv.op == KV_OP_GET) {
            for(int i=0; i<KV_TABLE_MAX; i++) {
                if(strcmp(table[i].key, payload_recv.key) == 0) {
                    printf("GET K: %s V: %s\n", payload_recv.key, table[i].value);
                    strncpy(value, table[i].value, KV_VALUE_MAX-1);
                    memcpy(msg.payload, &value, sizeof(value));
                    msg.payload_len = sizeof(value);
                    ch++;
                    break;
                }
            }
            if(ch == 0)
              printf("NOT FOUND\n");
        }
        /*
        Sending reply to core
        payload structure is just char[] value, not kv_payload struct
        GET: value in payload
        PUT: empty payload
        */
        if(ioctl(fd, SIMPLE_IPC_IOC_SEND_REPLY, &msg) == -1) {
            perror("IOCTL ERR");
            return EXIT_FAILURE;
        }
    }
    return 0;
}
