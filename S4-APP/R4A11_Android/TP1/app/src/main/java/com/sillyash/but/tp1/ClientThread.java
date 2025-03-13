package com.sillyash.but.tp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    protected int tableNumber;
    protected Product product;
    protected Socket socket;
    protected PrintWriter out;
    protected BufferedReader in;

    public ClientThread(int tableNumber, Product product) {
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
            throw new RuntimeException(e);
        }
    }

    protected void connect() throws IOException {
        this.socket = new Socket(ServerTools.URL, ServerTools.PORT);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    protected void sendOrder() {
        int productNumber = product.getNumber();

        String table = ServerTools.numToString(tableNumber);
        String order = ServerTools.numToString(productNumber);
        String msg = table + order;

        this.out.write(msg);
    }

    protected void closeConnection() throws IOException {
        this.out = null;
        this.in = null;

        this.socket.close();
    }
}
