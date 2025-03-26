package com.sillyash.but.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected ArrayList<Order> orders = new ArrayList<>();
    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<Ingredient> ingredients = new ArrayList<>();
    protected int tableNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("CREATE", this.getClass().getName());

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.initProducts();
        this.initIngredients();

        Intent i = getIntent();
        this.tableNumber = i.getIntExtra(StartActivity.TABLE, 0);

        TextView tv = this.findViewById(R.id.tableNumTextView);
        String desc = getString(R.string.tableDescription) + ' ' + this.tableNumber;
        tv.setText(desc);

        // If there is no saved instance state, return
        if (savedInstanceState == null) return;

        // Retrieve orders
        Log.v("InstanceState", "Retrieving orders...");
        ArrayList<Order> savedOrders = savedInstanceState.getParcelableArrayList("orders");

        int ordersCount = (savedOrders != null) ? savedOrders.size() : 0;
        Log.d("InstanceState", "Orders retrieved : " + ordersCount);
        this.orders.addAll(savedOrders);
    }

    protected void initProducts() {
        products.add(new Product(11, getString(R.string.napolitean), 12.99));
        products.add(new Product(5, getString(R.string.royale), 12.99));
        products.add(new Product(18, getString(R.string.mountain), 13.99));
        products.add(new Product(10, getString(R.string.fourSeasons), 14.5));
        products.add(new Product(20, getString(R.string.raclette), 12.99));
        products.add(new Product(6, getString(R.string.hawaii), 13.99));
        products.add(new Product(94, getString(R.string.pannaCotta), 3.6));
        products.add(new Product(91, getString(R.string.tiramisu), 2.99));
    }

    protected void initIngredients() {
        ingredients.add(new Ingredient(getString(R.string.mozzarella)));
        ingredients.add(new Ingredient(getString(R.string.gorgonzola)));
        ingredients.add(new Ingredient(getString(R.string.anchovies)));
        ingredients.add(new Ingredient(getString(R.string.capers)));
        ingredients.add(new Ingredient(getString(R.string.olives)));
        ingredients.add(new Ingredient(getString(R.string.artichokes)));
        ingredients.add(new Ingredient(getString(R.string.rawHam)));
        ingredients.add(new Ingredient(getString(R.string.cookedHam)));
    }

    public void setTableText(String msg) {
        TextView tv = findViewById(R.id.tableNumTextView);
        tv.setText(msg);
    }

    protected void onSaveInstanceState (@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("orders", this.orders);
        Log.i("InstanceState", "Orders saved");
    }

    void orderProduct(Product p) {
        Log.i("Server", "Starting client thread");
        ClientThread ct = new ClientThread(this, this.tableNumber, p);
        ct.start();
    }

    @Override
    protected void onStart() { super.onStart(); Log.i("START", this.getClass().getName()); }
    @Override
    protected void onRestart() { super.onRestart(); Log.i("RESTART", this.getClass().getName()); }
    @Override
    protected void onResume() { super.onResume(); Log.i("RESUME", this.getClass().getName()); }
    @Override
    protected void onPause() { super.onPause(); Log.i("PAUSE", this.getClass().getName()); }
    @Override
    protected void onStop() { super.onStop(); Log.i("STOP", this.getClass().getName()); }
    @Override
    protected void onDestroy() { super.onDestroy(); Log.i("DESTROY", this.getClass().getName()); }
}
