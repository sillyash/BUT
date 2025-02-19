package com.sillyash.but.tp1;

import java.util.ArrayList;
public class Order {
    protected Product product;
    protected int quantity;

    public Order(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void addQuantity() { this.quantity++; }
    public int getQuantity() { return this.quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Product getProduct() { return this.product; }
    public void setProduct(Product product) { this.product = product; }

    public static double getTotal(ArrayList<Order> orders) {
        double total = 0;
        for (Order order : orders) {
            total += order.product.price * order.quantity;
        }
        return total;
    }
}
