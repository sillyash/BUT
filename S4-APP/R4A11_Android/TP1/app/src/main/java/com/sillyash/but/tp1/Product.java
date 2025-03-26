package com.sillyash.but.tp1;

import android.util.Log;
import java.util.List;
import android.widget.Button;

public class Product {
    protected String name;
    protected double price;
    protected int number;

    public Product(int number, String name, double price) {
        this.number = number;
        this.name = name;
        this.price = price;
    }

    public static String getProductName(Button b) {
        String name = b.getText().toString();
        name = name.split(":")[0]; // Remove number and comma
        name = name.trim();              // Remove trailing whitespace(s)
        return name;
    }

    public static Product getProduct(List<Product> products, String name) {
        Product product = null;
        for (Product p : products) {
            if (p.getName().equals(name)) {
                product = p;
                break;
            }
        }

        if (product == null) {
            Log.e("Product", "Product not found : " + name);
        } else {
            Log.d("Product", "Product found : " + product.getName());
        }

        return product;
    }

    public String getName() { return this.name; }
    public double getPrice() { return this.price; }
    public int getNumber() { return this.number; }
}
