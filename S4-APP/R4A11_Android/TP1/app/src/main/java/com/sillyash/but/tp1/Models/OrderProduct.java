package com.sillyash.but.tp1.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.List;

import androidx.annotation.NonNull;

import com.sillyash.but.tp1.Activities.MainActivity;

import java.util.ArrayList;

public class OrderProduct implements Parcelable {
    protected Product product;
    protected int quantity;

    public OrderProduct(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public OrderProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public OrderProduct(Parcel in) {
        int indexProduct = in.readInt();
        this.product = MainActivity.products.get(indexProduct);
        this.quantity = in.readInt();
    }

    public static final Creator<OrderProduct> CREATOR = new Creator<OrderProduct>() {
        @Override
        public OrderProduct createFromParcel(Parcel in) {
            return new OrderProduct(in);
        }

        @Override
        public OrderProduct[] newArray(int size) {
            return new OrderProduct[size];
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

    public static OrderProduct getOrSetOrder(List<OrderProduct> orderProducts, Product p) {
        OrderProduct orderProduct = null;
        for (OrderProduct o : orderProducts) {
            String productOrder = o.getProduct().getName();
            if (productOrder.equals(p.getName())) {
                orderProduct = o;
                break;
            }
        }

        if (orderProduct == null) {
            orderProduct = new OrderProduct(p);
            orderProducts.add(orderProduct);
            Log.i("Order", "Order created : " + orderProduct.getProduct().getName());
        } else {
            orderProduct.addQuantity();
            Log.i("Order", "Order updated : " + orderProduct.getProduct().getName());
        }

        return orderProduct;
    }

    public static OrderProduct getOrder(List<OrderProduct> orderProducts, Product p) {
        OrderProduct orderProduct = null;
        for (OrderProduct o : orderProducts) {
            String productOrder = o.getProduct().getName();
            if (productOrder.equals(p.getName())) {
                orderProduct = o;
                break;
            }
        }

        return orderProduct;
    }

    public void addQuantity() { this.quantity++; }
    public int getQuantity() { return this.quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Product getProduct() { return this.product; }
    public void setProduct(Product product) { this.product = product; }

    public static double getTotal(ArrayList<OrderProduct> orderProducts) {
        double total = 0;
        for (OrderProduct orderProduct : orderProducts) {
            total += orderProduct.product.price * orderProduct.quantity;
        }
        return total;
    }
}
