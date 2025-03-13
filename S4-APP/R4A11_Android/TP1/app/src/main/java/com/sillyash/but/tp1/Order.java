package com.sillyash.but.tp1;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Order implements Parcelable {
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

    public Order(Parcel in) {
        int indexProduct = in.readInt();
        this.product = MainActivity.products.get(indexProduct);
        this.quantity = in.readInt();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(MainActivity.products.indexOf(product));
        parcel.writeInt(quantity);
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
