package com.sillyash.but.tp1.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.sillyash.but.tp1.Activities.MainActivity;

import java.util.List;

public class OrderIngredient implements Parcelable {
    protected Ingredient ingredient;
    protected int quantity;

    public OrderIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        this.quantity = 1;
    }

    public OrderIngredient(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public OrderIngredient(Parcel in) {
        int indexIngredient = in.readInt();
        this.ingredient = MainActivity.ingredients.get(indexIngredient);
        this.quantity = in.readInt();
    }

    public static final Creator<OrderIngredient> CREATOR = new Creator<OrderIngredient>() {
        @Override
        public OrderIngredient createFromParcel(Parcel in) {
            return new OrderIngredient(in);
        }

        @Override
        public OrderIngredient[] newArray(int size) {
            return new OrderIngredient[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(MainActivity.ingredients.indexOf(ingredient));
        parcel.writeInt(quantity);
    }

    public static OrderIngredient getOrSetOrder(List<OrderIngredient> orderIngredients, Ingredient ing) {
        OrderIngredient orderIngredient = null;
        for (OrderIngredient o : orderIngredients) {
            String ingredientOrder = o.getIngredient().getName();
            if (ingredientOrder.equals(ing.getName())) {
                orderIngredient = o;
                break;
            }
        }

        if (orderIngredient == null) {
            orderIngredient = new OrderIngredient(ing);
            orderIngredients.add(orderIngredient);
            Log.i("Order", "Order created : " + orderIngredient.getIngredient().getName());
        } else {
            orderIngredient.addQuantity();
            Log.i("Order", "Order updated : " + orderIngredient.getIngredient().getName());
        }

        return orderIngredient;
    }

    public static OrderIngredient getOrder(List<OrderIngredient> orderIngredients, Ingredient ing) {
        OrderIngredient orderIngredient = null;
        for (OrderIngredient o : orderIngredients) {
            String ingredientOrder = o.getIngredient().getName();
            if (ingredientOrder.equals(ing.getName())) {
                orderIngredient = o;
                break;
            }
        }

        return orderIngredient;
    }

    public void addQuantity() { this.quantity++; }
    public int getQuantity() { return this.quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Ingredient getIngredient() { return this.ingredient; }
    public void setProduct(Ingredient ingredient) { this.ingredient = ingredient; }
}
