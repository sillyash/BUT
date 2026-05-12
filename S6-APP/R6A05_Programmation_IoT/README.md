# R6A05 - Programmation IoT

Ash MERIENNE

## Cheat sheet

### Setup

> [!NOTE]
>
> This is *optional*, only do if you have trouble with
> __renode__ (`LoadPlatformDescription` errors).

```bash
cd /opt/zephyr-.../
./setup.sh -t all -c
```

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