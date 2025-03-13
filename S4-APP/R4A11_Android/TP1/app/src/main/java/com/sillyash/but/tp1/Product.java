package com.sillyash.but.tp1;

public class Product {
    protected String name;
    protected double price;
    protected int number;

    public Product(int number, String name, double price) {
        this.number = number;
        this.name = name;
        this.price = price;
    }

    public String getName() { return this.name; }
    public double getPrice() { return this.price; }
    public int getNumber() { return this.number; }
}
