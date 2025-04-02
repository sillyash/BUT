package com.sillyash.but.tp1.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sillyash.but.tp1.Activities.MainActivity;
import com.sillyash.but.tp1.Models.OrderProduct;
import com.sillyash.but.tp1.Models.Product;
import com.sillyash.but.tp1.R;

import java.util.ArrayList;

public class PizzaFragment extends Fragment {
    protected MainActivity activity;
    protected View view;
    protected ArrayList<Button> btnsOrder = new ArrayList<>();

    public PizzaFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_pizza, container, false);

        Button btnDeleteOrders = view.findViewById(R.id.btnDeleteOrders);
        btnDeleteOrders.setOnClickListener( v -> {
            this.activity.orderProducts.clear();
            this.updateButtons();
        });

        this.setIngredientButton();
        this.initButtons();
        this.updateButtons();
        return this.view;
    }

    protected void initButtons() {
        this.setPizzaButton(R.id.btn1);
        this.setPizzaButton(R.id.btn2);
        this.setPizzaButton(R.id.btn3);
        this.setPizzaButton(R.id.btn4);
        this.setPizzaButton(R.id.btn5);
        this.setPizzaButton(R.id.btn6);
        this.setPizzaButton(R.id.btn7);
        this.setPizzaButton(R.id.btn8);
    }

    protected void setPizzaButton(int btnID) {
        Button btn = this.view.findViewById(btnID);
        this.btnsOrder.add(btn);
        this.setListener(btn);
    }

    protected void setIngredientButton() {
        Button btnCustomPizza = this.view.findViewById(R.id.btnCustomPizza);
        btnCustomPizza.setOnClickListener( v -> {
            // Get the FragmentManager
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager(); // Or getChildFragmentManager() if this is in a Fragment

            // Create a new instance of PizzaFragment
            IngredientFragment ingredientFragment = new IngredientFragment();

            // Begin a FragmentTransaction
            fragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, ingredientFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    protected void updateButtons() {
        for (Button btn : btnsOrder) {
            String productName = Product.getProductName(btn);
            this.resetButtonText(btn, productName);

            Product p = Product.getProduct(activity.products, productName);
            OrderProduct o = OrderProduct.getOrder(activity.orderProducts, p);

            if (o != null) this.updateButtonText(btn, o , p);
        }
    }

    protected void setListener(Button btn) {
        Log.v("BUTTON", "Setting listener on " + btn);
        btn.setOnClickListener(v -> {
            Button btn1 = (Button) v;

            String name = Product.getProductName(btn1);
            Product p = Product.getProduct(activity.products, name);
            OrderProduct o = OrderProduct.getOrSetOrder(activity.orderProducts, p);

            updateButtonText(btn1, o, p);
            Log.i("ORDER", "Ordering...");
            activity.orderProduct(p);
        });
    }

    void resetButtonText(Button btn, String productName) {
        btn.setText(productName);
    }

    void updateButtonText(Button b, OrderProduct o, Product p) {
        String s = p.getName();
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
