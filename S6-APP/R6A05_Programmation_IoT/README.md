# R6A05 - Programmation IoT

Ash MERIENNE

## Cheat sheet

### Vocabulary

|      |                                                |
|:-----|:-----------------------------------------------|
| RTOS | Real-time Operating System                     |
| UART | Universal asynchronous receiver-transmitter    |
| GPIO | General Purpose Input/Output                   |
| TX   | Transmitter buffer (UART)                      |
| RX   | Receiver buffer (UART)                         |
| IER  | Interrupt Enable Register                      |
| ISR  | Interrupt Service Routine                      |
| IRQ  | Interrupt Request                              |
| GDB  | GNU Debugger                                   |
| MPU  | Memory Protection Unit                         |

### Setup

#### Load docker image

```bash
# Optional, only if you need to load the image
/Pub/s6/progAdv/scripts/individual/loadZephyrFromSavedImage.sh

# Spin up a container with mounting, etc.
/Pub/s6/progAdv/scripts/individual/run/executeZephyrDocker_debian_host.sh
```

> [!NOTE]
>
> There is a mount between the container and the host.
> It can be very useful for loading / exporting source code:
> 
> Example (host):
> 
> ```
> cd ~/workspace/root/src
> cp -r ~/R6A05_Programmation_IoT/Module_4/* .
> ```

#### Update / debug Zephyr

```bash
cd /opt/zephyr-.../
./setup.sh -t all -c
```

> [!NOTE]
>
> This is __*optional*__, only do if you have trouble
> with __Renode__ (`LoadPlatformDescription` errors).

Ensure internet is available, then:

```bash
cd /workspace/zephyrproject/src
west update
west export-zephyr # Optional
```

### Project tree

```
.
├── CMakeLists.txt  # Build config
├── prj.conf        # CMake extra flags
└── src/            # Source code
    └── main.c
```

### Build

```bash
west build -b nrf52840dk/nrf52840 .
```

Specify dir (practical for multiple node projects).

```bash
west build -b nrf52840dk/nrf52840 -s [node_dir] -d [node_dir/build]
```

#### Build debug

```bash
west build -b nrf52840dk/nrf52840 -p always -d build_debug
```

### Renode

```bash
renode
```

### Run a script

```bash
include @my_renode_script.resc
```

### Create a machine

```bash
mach create "node_name"
```

### Load a platform description

```bash
machine LoadPlatformDescription @platforms/boards/my_device.repl

# or

machine LoadPlatformDescription @platforms/cpus/my_device.repl
```

### Load Zephyr build

```bash
sysbus LoadELF @path/to/src/build/zephyr/zephyr.elf
```

### Show analyzer

```bash
showAnalyzer sysbus.uart0
```

### Debug using GDB

```bash
machine StartGdbServer 3333
```

On another terminal __*in the Zephyr docker container*__,
run:

```bash
gdb-multiarch build_debug/zephyr/zephyr.elf # path to your build
(gdb) target remote :3333
```

#### Breakpoint

```bash
(gdb) b func_name
(gdb) b line_number
```

#### Commands

```bash
(gdb) c     # continue
(gdb) r     # run
(gdb) n     # next
(gdb) bt    # backtrace
(gdb) print c
```

### Hub management

#### Create a hub

In *Renode*, __not in a machine__, execute:

```bash
emulation CreateUARTHub "hub_name"
```

#### Connect a node to a hub

In *Renode*, __inside a machine__, execute:

```bash
connector Connect sysbus.uart0 hub_name
```

### Show data

```bash
showAnalyzer my_hardware_bus

# Example
showAnalyzer sysbus.uart0
```

### Logging

```bash
logLevel -1 my_hardware_bus

# Example
logLevel -1 sysbus.uart0
logLevel -1 sysbus.gpio0
```

### Simulate button press

```bash
sysbus.gpio0.sw0 Press
sysbus.gpio0.sw0 Release
```

### Run simulation

```bash
start
```

## Programming guidelines

### Single node / general guidelines

#### Find header files and documentation

| Option   | URL / Guidelines                       |
|:---------|:---------------------------------------|
| Option A | Search "Zephyr API xyz"                |
| Option B | https://docs.zephyrproject.org/        |

> [!NOTE]
>
> Keep in mind we are using version __4.3.0__ of Zephyr.
>
> For example, avoid using Nordic's documentation as it is
> quite outdated (IoT moves fast !).

#### PRJ file

```bash
CONFIG_PRINTK=y
CONFIG_SERIAL=y

# std out - depends on zephyr version
CONFIG_CONSOLE=y
CONFIG_STDOUT_CONSOLE=y

# Determinist stack size universally on devices
CONFIG_MAIN_STACK_SIZE=1024

# GPIO
CONFIG_GPIO=y

# UART
CONFIG_UART_CONSOLE=y
CONFIG_UART_INTERRUPT_DRIVEN=y

# LOGGING
CONFIG_LOG=y
CONFIG_LOG_MODE_DEFERRED=y
CONFIG_LOG_DEFAULT_LEVEL=3

# Debug Specifics (-O0)
CONFIG_NO_OPTIMIZATIONS=y

# Debugging configuration
CONFIG_THREAD_NAME=y
CONFIG_THREAD_ANALYZER=y
CONFIG_THREAD_ANALYZER_AUTO=y
CONFIG_THREAD_ANALYZER_RUN_UNLOCKED=y
CONFIG_THREAD_ANALYZER_USE_PRINTK=y
CONFIG_CONSOLE=y
```

#### Logging

In Zephyr log levels are:

```bash
NONE = 0
ERR  = 1
WRN  = 2
INFO = 3
DBG  = 4
```

```c
LOG_MODULE_REGISTER(uart_receiver, LOG_LEVEL_INF);
printk(...) /* Gets logged */
```

```log
[00:00:00.123,456] <inf> uart_receiver: ...
```

#### Common header files

```c
#include <string.h>
#include <stdlib.h>
#include <time.h>

#include <zephyr/kernel.h>          /* Core Zephyr kernel functionality */
#include <zephyr/sys/printk.h>      /* Kernel printing functions        */

#include <zephyr/drivers/gpio.h>    /* GPIO control                     */
#include <zephyr/drivers/uart.h>    /* UART control                     */
#include <zephyr/logging/log.h>     /* Logging                          */
#include "cJSON.h"                  /* cJSON (must be in lib/)          */
```

#### Various functions

| Function / method         | Usage                                 |
|:--------------------------|:--------------------------------------|
| `printk()`                | Prints to *serial* output             |
| `k_sleep(K_SECONDS(s))`   | Sleeps for `s` seconds                |
| `k_msleep(s)`             | Sleep for `s` milliseconds            |
|-|-|
| `gpio_is_ready_dt()`      | Check if GPIO device is ready         |
| `gpio_pin_configure_dt()` | Configure GPIO pin as output          |
| `gpio_pin_toggle_dt()`    | Toggle a GPIO device (LED, etc.)      |
| `gpio_pin_get_dt()`       | Get a GPIO value (button, etc.)       |
|-|-|
| `uartHub`                 | Central communication hub UART        |
| `BaudRate 115200`         | Standard transmission rate            |
| `uart_poll_out()`         | Send a byte in polling mode           |
| `uart_poll_in()`          | Receive a byte in polling mode        |
| `uart_irq_callback_set()` | Configure a callback for interrupts   |
|-|-|
| `k_timer_init(&t, c, s)`  | Configure a timer, with callback `c` and stop `s` functions |
| `k_timer_start(&t, p, d)` | Start a timer, with `p` period and `d` initial delay in `K_SECONDS` |
|-|-|
| `k_work_init(&w, h)`      | Initialize a worker `w` with handler function `h` |
| `k_msgq_put(&q, &c, K_NO_WAIT)` | 

### UART

UART communication can be implemented either with __polling__ or
__interrupts__.

Cf. Module 2.

#### Device tree and definition

To use a UART device, you need to *define* that device.*

To define thhat device, yo u need to *retrieve* it.

To retrieve that device, you need to use the *device tree*.

```c
/*
 * DT -> Device tree
 * Search by nodelabel
 * Agnostic reference material uart_dev
 */
#define UART_DEVICE_NODE DT_NODELABEL(uart0)
const struct device *uart_dev;
uart_dev = DEVICE_DT_GET(UART_DEVICE_NODE);

/*
 * DT -> Device tree
 * Search by alias
 * Create ref to device -> &device (ref)
 */
#define GPIO_NODE DT_ALIAS(led0)
static const struct gpio_dt_spec device = GPIO_DT_SPEC_GET(GPIO_NODE, gpios);
```

#### Polling

Polling is simpler burt drains CPU usage more (and battery!).

#### Interrupt

Interrupts are more efficient but need proper callback handling.

You need to enable RX/TX interrupt 

#### Timer based

Makes polling more efficient by essentially substituting programming
loops and sleep calls to a native Zephyr timer based approach,
allowing the thread to fully sleep and save CPU and energy.

```c
static struct k_timer my_timer;
```

Cf. Module 3

#### Asynnchronous

Uses __k_msgq__ (message queue) and __k_work__ (async message handling)
to make UART robust, modular, and closer to event-based programming.

```c
#define MSG_SIZE 64
K_MSGQ_DEFINE(uart_msgq, sizeof(uint8_t), MSG_SIZE, 4);

struct k_work uart_work;
```

Cf. Module 4

#### Structured messages and collector server

Uses __sensor nodes__ and a __server node__.

```c
struct msg {
    uint32_t ...;
    uint8_t ...; 
} __packed; /* packed removes padding in struct */

Cf. Module 5
