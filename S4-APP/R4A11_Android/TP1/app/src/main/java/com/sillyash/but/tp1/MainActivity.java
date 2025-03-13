package com.sillyash.but.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected ArrayList<Button> btnsOrder = new ArrayList<>();
    protected ArrayList<Order> orders = new ArrayList<>();
    public static ArrayList<Product> products = new ArrayList<>();
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

        this.initButtons();
        this.initProducts();

        Button btnDeleteOrders = findViewById(R.id.btnDeleteOrders);
        btnDeleteOrders.setOnClickListener( v -> {
            orders.clear();
            this.updateButtons();
        });

        Intent i = getIntent();
        this.tableNumber = i.getIntExtra(StartActivity.TABLE, 0);

        TextView tv = findViewById(R.id.tableNumTextView);
        String desc = getString(R.string.tableDescription) + ' ' + this.tableNumber;
        tv.setText(desc);

        // If there is no saved instance state, return
        if (savedInstanceState == null) return;

        // Retrieve orders
        Log.v("InstanceState", "Retrieving orders...");
        ArrayList<Order> savedOrders = savedInstanceState.getParcelableArrayList("orders");
        Log.d("InstanceState", "Orders retrieved : " + savedOrders.size());
        this.orders.addAll(savedOrders);

        this.updateButtons();
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

    protected void updateButtons() {
        for (Button btn : btnsOrder) {
            // Get product name
            String productName = btn.getText().toString();
            productName = productName.split(":")[0];
            productName = productName.trim();

            // Reset button text
            btn.setText(productName);

            Order o = null;
            Product p = null;
            for (Order order : orders) {
                Product product = order.getProduct();
                String orderProductName = product.getName();
                if (orderProductName.equals(productName)) {
                    o = order;
                    p = product;
                    break;
                }
            }

            if (o == null) continue;

            // Update button text
            btn.setText(o.getProduct().getName() + " : " + o.getQuantity());

            ClientThread ct = new ClientThread(this.tableNumber, p);
            ct.run();
        }
    }


    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("orders", this.orders);
        Log.i("InstanceState", "Orders saved");
    }

    protected void setPizzaButton(int btnID) {
        Button btn = findViewById(btnID);
        this.btnsOrder.add(btn);
        this.setListener(btn);
    }

    protected void setListener(Button btn) {
        btn.setOnClickListener(v -> {
            Button b = (Button) v;
            String name = b.getText().toString();
            // Remove number and comma
            name = name.split(":")[0];
            // Remove trailing whitespace
            name = name.trim();

            Product product = null;
            for (Product p : products) {
                if (p.getName().equals(name)) {
                    product = p;
                    break;
                }
            }

            if (product == null) {
                Log.e("Product", "Product not found : " + name);
                return;
            } else {
                Log.d("Product", "Product found : " + product.getName());
            }

            Order order = null;
            for (Order o : orders) {
                String productOrder = o.getProduct().getName();
                if (productOrder.equals(product.getName())) {
                    order = o;
                    break;
                }
            }

            if (order == null) {
                order = new Order(product);
                orders.add(order);
                Log.i("Order", "Order created : " + order.getProduct().getName());
            } else {
                order.addQuantity();
                Log.i("Order", "Order updated : " + order.getProduct().getName());
            }

            b.setText(product.getName() + " : " + order.getQuantity());
        });
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
