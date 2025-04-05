package com.sillyash.but.tp1.Server;

import android.util.Log;

import androidx.fragment.app.DialogFragment;

import com.sillyash.but.tp1.Fragments.PizzaDialog;
import com.sillyash.but.tp1.Models.Ingredient;
import com.sillyash.but.tp1.Activities.MainActivity;
import com.sillyash.but.tp1.Models.OrderIngredient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientThreadCustomPizza extends Thread {
    protected MainActivity activity;
    protected int tableNumber;
    protected List<OrderIngredient> ingredients;
    protected Socket socket;
    protected PrintWriter out;
    protected BufferedReader in;

    public ClientThreadCustomPizza(MainActivity activity, int tableNumber, List<OrderIngredient> ingredients) {
        this.activity = activity;
        this.tableNumber = tableNumber;
        this.ingredients = ingredients;
    }

    @Override
    public void run() {
        try {
            this.connect();
            this.sendOrder();
            this.getResponses();
            this.closeConnection();
        }

        catch (IOException e) {
            Log.e("Server", e.getMessage());
        }
    }

    protected void connect() throws IOException {
        this.socket = new Socket(ServerTools.URL, ServerTools.PORT);
        this.out = new PrintWriter(socket.getOutputStream());
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    protected void sendOrder() throws IOException {
        String table = ServerTools.numToString(tableNumber);
        String clientMsg = table + Ingredient.CODE;

        int i = 0;
        for (OrderIngredient ord : ingredients) {
            if (i > 0) clientMsg += " + ";
            clientMsg += ord.getIngredient().getServerName();
            i++;
        }

        this.activity.orderIngredients.clear();

        this.out.write(clientMsg);
        Log.i("Server", "Ordering " + clientMsg);
        this.out.flush();
    }

    protected void getResponses() throws IOException {
        String serverMsg = this.in.readLine();
        Log.i("Server", serverMsg);
        this.activity.setTableText(serverMsg);
        PizzaDialog pd = new PizzaDialog(serverMsg);
        pd.show(activity.getSupportFragmentManager(), "PizzaDialog");

        serverMsg = this.in.readLine();
        Log.i("Server", serverMsg);
        this.activity.setTableText(serverMsg);
        pd.setMessage(serverMsg);
        pd.show(activity.getSupportFragmentManager(), "PizzaDialog");
    }

    protected void closeConnection() throws IOException {
        this.out = null;
        this.in = null;

        this.socket.close();
    }
}
