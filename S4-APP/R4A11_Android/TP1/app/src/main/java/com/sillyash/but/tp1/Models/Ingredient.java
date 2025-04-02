package com.sillyash.but.tp1.Models;

import android.util.Log;
import android.widget.Button;

import com.sillyash.but.tp1.R;

import java.util.List;

public class Ingredient {
    public static final String MOZZARELLA = "mozzarella";
    public static final String GORGONZOLA = "gorgonzola";
    public static final String ANCHOVIES = "anchois";
    public static final String CAPERS = "câpres";
    public static final String OLIVES = "olives";
    public static final String ARTICHOKES = "artichauds";
    public static final String RAWHAM = "jambon cru";
    public static final String COOKEDHAM = "jambon cuit";
    public static final int CODE = 50;
    protected String name;
    protected String serverName;

    public Ingredient(String name, String serverName) {
        this.name = name;
        this.serverName = serverName;
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

    public String getServerName() { return this.serverName; }
}
