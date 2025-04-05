package com.sillyash.but.tp1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.sillyash.but.tp1.Fragments.IngredientFragment;
import com.sillyash.but.tp1.Fragments.PizzaFragment;
import com.sillyash.but.tp1.Models.Ingredient;
import com.sillyash.but.tp1.Models.OrderIngredient;
import com.sillyash.but.tp1.Models.OrderProduct;
import com.sillyash.but.tp1.Models.Product;
import com.sillyash.but.tp1.R;
import com.sillyash.but.tp1.Server.ClientThreadCustomPizza;
import com.sillyash.but.tp1.Server.ClientThreadPizza;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public ArrayList<OrderProduct> orderProducts = new ArrayList<>();
    public ArrayList<OrderIngredient> orderIngredients = new ArrayList<>();
    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<Ingredient> ingredients = new ArrayList<>();
    public static int currentView = 0;
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
        if (savedInstanceState == null) {
            this.loadView();
            return;
        }

        // Retrieve orders
        Log.v("InstanceState", "Retrieving orders...");
        ArrayList<OrderProduct> savedOrderProducts = savedInstanceState.getParcelableArrayList("orders");

        // Retrieve ingredients
        Log.v("InstanceState", "Retrieving ingredients...");
        ArrayList<OrderIngredient> savedIngredients = savedInstanceState.getParcelableArrayList("ingredients");

        int ordersCount = (savedOrderProducts != null) ? savedOrderProducts.size() : 0;
        Log.d("InstanceState", "Orders retrieved : " + ordersCount);
        this.orderProducts.addAll(savedOrderProducts);

        int ingredientsCount = (savedIngredients != null) ? savedIngredients.size() : 0;
        Log.d("InstanceState", "Ingredients retrieved : " + ingredientsCount);
        this.orderIngredients.addAll(savedIngredients);

        int view = savedInstanceState.getInt("currentView");
        currentView = view;
        this.loadView();
    }

    public void loadView() {
        // Get the FragmentManager
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        // Create a new instance of PizzaFragment
        Fragment frag = null;

        if (currentView == 0) frag = new PizzaFragment();
        else frag = new IngredientFragment();

        // Begin a FragmentTransaction
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, frag)
                .addToBackStack(null)
                .commit();
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
        ingredients.add(new Ingredient(getString(R.string.mozzarella), Ingredient.MOZZARELLA));
        ingredients.add(new Ingredient(getString(R.string.gorgonzola), Ingredient.GORGONZOLA));
        ingredients.add(new Ingredient(getString(R.string.anchovies), Ingredient.ANCHOVIES));
        ingredients.add(new Ingredient(getString(R.string.capers), Ingredient.CAPERS));
        ingredients.add(new Ingredient(getString(R.string.olives), Ingredient.OLIVES));
        ingredients.add(new Ingredient(getString(R.string.artichokes), Ingredient.ARTICHOKES));
        ingredients.add(new Ingredient(getString(R.string.rawHam), Ingredient.RAWHAM));
        ingredients.add(new Ingredient(getString(R.string.cookedHam), Ingredient.COOKEDHAM));
    }

    public void setTableText(String msg) {
        TextView tv = findViewById(R.id.tableNumTextView);
        tv.setText(msg);
    }

    protected void onSaveInstanceState (@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("orders", this.orderProducts);
        outState.putParcelableArrayList("ingredients", this.orderIngredients);
        outState.putInt("currentView", currentView);
        Log.i("InstanceState", "Orders saved");
    }

    public void orderProduct(Product p) {
        Log.i("Server", "Starting client thread");
        ClientThreadPizza ct = new ClientThreadPizza(this, this.tableNumber, p);
        ct.start();
    }

    public void orderIngredients(List<OrderIngredient> orders) {
        Log.i("Server", "Starting client thread");
        ClientThreadCustomPizza ct = new ClientThreadCustomPizza(this, this.tableNumber, orders);
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
