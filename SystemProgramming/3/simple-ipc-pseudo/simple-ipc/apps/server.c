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

static void cleanup_server(void) {
    // TODO: Release resources acquired by the server before termination.
}

static void handle_signal(int signo) {
    (void)signo;
    // TODO: Implement signal action handler.
}

int main(int argc, char **argv) {
    struct sigaction sa;

    if (argc != 2) {
        fprintf(stderr, "Usage: %s <endpoint>\n", argv[0]);
        return 1;
    }

    memset(&sa, 0, sizeof(sa));
    sa.sa_handler = handle_signal;
    sigemptyset(&sa.sa_mask);

    sigaction(SIGINT, &sa, NULL);
    sigaction(SIGTERM, &sa, NULL);

    atexit(cleanup_server);

    // TODO: Implement the server main loop.

    return 0;
}
