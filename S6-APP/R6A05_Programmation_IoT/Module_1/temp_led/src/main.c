/**
 * @file main.c
 * @brief Temperature simulation with LED indicator
 *
 * This program simulates temperature readings and toggles an LED every 5 seconds.
 * The temperature values are randomly generated between 20°C and 29°C.
 */

/* Include necessary header files */
#include <zephyr/kernel.h>    /* Core Zephyr kernel functionality */
#include <zephyr/drivers/gpio.h> /* GPIO control for LED */
#include <zephyr/sys/printk.h>  /* Kernel printing functions */
#include <stdlib.h>             /* For rand() function */
#include <time.h>               /* Time-related functions */

/* Define the LED using the device tree alias */
#define LED0_NODE DT_ALIAS(led0) /* Gets the device tree node for LED0 alias */

/* Create a GPIO specification structure for the LED using device tree information */
static const struct gpio_dt_spec led = GPIO_DT_SPEC_GET(LED0_NODE, gpios);

/**
 * @brief Main application entry point
 *
 * Initializes GPIO for LED control, then enters a loop that:
 * 1. Generates a random temperature value
 * 2. Outputs temperature to console
 * 3. Toggles the LED state
 * 4. Prints the current LED state
 * 5. Waits for 5 seconds before repeating
 */
void main(void)
{
    /* Check if the LED GPIO device is ready */
    if (!gpio_is_ready_dt(&led)) {
        /* Exit if the LED GPIO is not available */
        return;
    }

    /* Configure the LED pin as output with initial state inactive (off) */
    gpio_pin_configure_dt(&led, GPIO_OUTPUT_INACTIVE);

    /* Main application loop */
    while (1) {
        /* Generate a random temperature between 20 and 29 degrees Celsius */
        int temp = 20 + rand() % 10;

        /* Print the current simulated temperature */
        printk("Temperature: %d \n", temp);

        /* Toggle the LED state (ON->OFF or OFF->ON) */
        gpio_pin_toggle_dt(&led);

        /* Print the current LED state (1=ON, 0=OFF) */
        printk("LED stato: %d\n", gpio_pin_get_dt(&led));

        /* Sleep for 5 seconds before the next iteration */
        k_sleep(K_SECONDS(5));
    }
}

