package com.sillyash.but.tp1;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    protected MainActivity activity;
    protected int tableNumber;
    protected Product product;
    protected Socket socket;
    protected PrintWriter out;
    protected BufferedReader in;

    public ClientThread(MainActivity activity, int tableNumber, Product product) {
        this.activity = activity;
        this.tableNumber = tableNumber;
        this.product = product;
    }

    @Override
    public void run() {
        try {
            this.connect();
            this.sendOrder();
            this.closeConnection();
        } catch (IOException e) {
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
