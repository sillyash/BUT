package com.sillyash.but.tp1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected ArrayList<Button> btnsOrder = new ArrayList<>(
            findViewById(R.layout.btn1)
    );
    protected ArrayList<Order> orders = new ArrayList<>();
    protected List<Product> products = Arrays.asList(
            new Product(getString(R.string.napolitean), 12.99),
            new Product(getString(R.string.royale), 12.99),
            new Product(getString(R.string.mountain), 13.99),
            new Product(getString(R.string.fourSeasons), 14.5),
            new Product(getString(R.string.raclette), 12.99),
            new Product(getString(R.string.hawaii), 13.99),
            new Product(getString(R.string.pannaCotta), 3.6),
            new Product(getString(R.string.tiramisu), 2.99)
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        for (int i = 1; i < 9; i++) {
            Button btn = findViewById(getResources().getIdentifier("btn"+i, "id", getPackageName()));
            this.btnsOrder.add(btn);
            this.setListener(btn);
        }
    }

    protected void setListener(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String name = b.getText().toString();

                Product product = null;
                for (Product p : products) {
                    if (p.getName().equals(name)) {
                        Log.v("Product", p.getName());
                        product = p;
                        break;
                    }
                }

                if (product == null) {
                    Log.println(Log.VERBOSE, "Product", "Product not found : " + name);
                    return;
                }

                Order order = null;
                for (Order o : orders) {
                    if (o.getProduct().getName().equals(product.getName())) {
                        Log.v("Order", o.getProduct().getName());
                        order = o;
                        return;
                    }
                }

                if (order == null) {
                    order = new Order(product);
                } else {
                    order.addQuantity();
                }

                b.setText(product.getName() + " " + order.getQuantity());
            }
        });
    }
}
