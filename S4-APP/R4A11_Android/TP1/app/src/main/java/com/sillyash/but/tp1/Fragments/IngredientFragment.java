package com.sillyash.but.tp1.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.FragmentManager;

import com.sillyash.but.tp1.Models.Ingredient;
import com.sillyash.but.tp1.Activities.MainActivity;
import com.sillyash.but.tp1.Models.OrderIngredient;
import com.sillyash.but.tp1.R;

import java.util.ArrayList;

public class IngredientFragment extends Fragment {
    protected MainActivity activity;
    protected View view;
    protected ArrayList<Button> btnsOrder = new ArrayList<>();

    public IngredientFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_ingredient, container, false);
        if (view == null) Log.e("PIZZAFRAGMENT", "View is null");

        this.setBackButton();
        this.setOrderButton();
        this.initButtons();
        this.updateButtons();
        return this.view;
    }

    protected void initButtons() {
        this.setIngredientButton(R.id.btn1);
        this.setIngredientButton(R.id.btn2);
        this.setIngredientButton(R.id.btn3);
        this.setIngredientButton(R.id.btn4);
        this.setIngredientButton(R.id.btn5);
        this.setIngredientButton(R.id.btn6);
        this.setIngredientButton(R.id.btn7);
        this.setIngredientButton(R.id.btn8);
    }

    protected void setIngredientButton(int btnID) {
        Button btn = this.view.findViewById(btnID);
        this.btnsOrder.add(btn);
        this.setListener(btn);
    }

    protected void setBackButton() {
        Button btnBackToPizza = this.view.findViewById(R.id.btnBackToPizza);
        if (btnBackToPizza == null) Log.e("PIZZAFRAGMENT", "btnBackToPizza is null");

        btnBackToPizza.setOnClickListener(v -> {
            // Get the FragmentManager
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

            // Create a new instance of PizzaFragment
            PizzaFragment pizzaFragment = new PizzaFragment();

            // Begin a FragmentTransaction
            fragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, pizzaFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    protected void updateButtons() {
        for (Button btn : btnsOrder) {
            String ingredientName = Ingredient.getIngredientName(btn);
            this.resetButtonText(btn, ingredientName);

            Ingredient ing = Ingredient.getIngredient(activity.ingredients, ingredientName);
            OrderIngredient o = OrderIngredient.getOrder(activity.orderIngredients, ing);

            if (o != null) this.updateButtonText(btn, o , ing);
        }
    }

    protected void setListener(Button btn) {
        Log.v("BUTTON", "Setting listener on " + btn);
        btn.setOnClickListener(v -> {
            Button btn1 = (Button) v;

            String name = Ingredient.getIngredientName(btn1);
            Ingredient ing = Ingredient.getIngredient(activity.ingredients, name);
            OrderIngredient o = OrderIngredient.getOrSetOrder(activity.orderIngredients, ing);

            updateButtonText(btn1, o, ing);
        });
    }

    protected void setOrderButton() {
        Button btn = this.view.findViewById(R.id.btnValidate);
        btn.setOnClickListener(v -> {

            Log.i("ORDER", "Ordering custom pizza");
            activity.orderIngredients(activity.orderIngredients);
        });
    }

    void resetButtonText(Button btn, String productName) {
        btn.setText(productName);
    }

    void updateButtonText(Button b, OrderIngredient o, Ingredient ing) {
        String s = ing.getName();
        s += " : ";
        s += o.getQuantity();
        b.setText(s);
    }

    @Override
    public void onStart() { super.onStart(); Log.i("START", this.getClass().getName()); }
    @Override
    public void onResume() { super.onResume(); Log.i("RESUME", this.getClass().getName()); }
    @Override
    public void onPause() { super.onPause(); Log.i("PAUSE", this.getClass().getName()); }
    @Override
    public void onStop() { super.onStop(); Log.i("STOP", this.getClass().getName()); }
    @Override
    public void onDestroy() { super.onDestroy(); Log.i("DESTROY", this.getClass().getName()); }
}
