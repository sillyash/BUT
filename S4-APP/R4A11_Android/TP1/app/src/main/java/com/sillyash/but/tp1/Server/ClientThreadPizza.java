package com.sillyash.but.tp1.Server;

import android.util.Log;

import com.sillyash.but.tp1.Activities.MainActivity;
import com.sillyash.but.tp1.Models.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThreadPizza extends Thread {
    protected MainActivity activity;
    protected int tableNumber;
    protected Product product;
    protected Socket socket;
    protected PrintWriter out;
    protected BufferedReader in;

    public ClientThreadPizza(MainActivity activity, int tableNumber, Product product) {
        this.activity = activity;
        this.tableNumber = tableNumber;
        this.product = product;
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
        Log.i("Server", "Ordering " + product.getName());
        int productNumber = product.getNumber();

        String table = ServerTools.numToString(tableNumber);
        String order = ServerTools.numToString(productNumber);
        String clientMsg = table + order;

        this.out.write(clientMsg);
        this.out.flush();
    }

    protected void getResponses() throws IOException {
        String serverMsg = this.in.readLine();
        Log.i("Server", serverMsg);
        this.activity.setTableText(serverMsg);

        serverMsg = this.in.readLine();
        Log.i("Server", serverMsg);
        this.activity.setTableText(serverMsg);
    }

    protected void closeConnection() throws IOException {
        this.out = null;
        this.in = null;

        this.socket.close();
    }
}
