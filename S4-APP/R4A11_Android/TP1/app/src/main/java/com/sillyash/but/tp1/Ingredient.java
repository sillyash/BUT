package com.sillyash.but.tp1;

import android.util.Log;
import android.widget.Button;

import java.util.List;

public class Ingredient {
    public static final int CODE = 50;
    protected String name;

    public Ingredient(String name) {
        this.name = name;
    }

    public static Ingredient getIngredient(List<Ingredient> ingredients, String name) {
        Ingredient ingredient = null;
        for (Ingredient i : ingredients) {
            if (i.getName().equals(name)) {
                ingredient = i;
                break;
            }
        }

        if (ingredient == null) {
            Log.e("Ingredient", "Ingredient not found : " + name);
        } else {
            Log.d("Ingredient", "Ingredient found : " + ingredient.getName());
        }

        return ingredient;
    }

    public static String getIngredientName(Button b) {
        String name = b.getText().toString();
        name = name.split(":")[0]; // Remove number and comma
        name = name.trim();              // Remove trailing whitespace(s)
        return name;
    }

    public String getName() { return this.name; }
}
