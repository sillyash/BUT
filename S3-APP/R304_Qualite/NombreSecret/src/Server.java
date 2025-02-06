import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Server
{
    public static final int DEFAULT_PORT = 3724;
    public static final int MIN_NUM = 0;
    public static final int MAX_NUM = 100;

    private static int readPort(Scanner sc) {
        System.out.print("Which port do you want to open ? (enter for default " + DEFAULT_PORT + ") >>> ");
        String read = sc.nextLine().replaceAll("\\s", "");
        int port;

        if (read.isEmpty()) return DEFAULT_PORT;
        try {
            port = Integer.parseInt(read);
        } catch (NumberFormatException e) {
            return -1;
        }
        if (port >= 1) return port;
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int serverPort = 0;
        boolean portOK = false;

        while (!portOK) {
            serverPort = readPort(sc);
            if (serverPort != -1) portOK = true;
        }

        ServerSocket ss;
        try {
            ss = new ServerSocket(serverPort);
        } catch (IOException e) {
            System.err.println("IOException : " + e.getMessage());
            return;
        }

        System.out.println("Server started ! Listening on port " + serverPort +"...");
        Socket client, client2;

        Random rng = new Random();
        int secretNum = rng.nextInt((MAX_NUM - MIN_NUM) + 1) + MIN_NUM;
        System.out.println("Secret number is " + secretNum + ".");

        try {
            client = ss.accept();
            System.out.println("Client request accepted !");

            client2 = ss.accept();
            System.out.println("Client request accepted !");
        } catch (IOException e) {
            System.err.println("IOException : " + e.getMessage());
            return;
        }

        try {
            ServerThread st1 = new ServerThread(client, secretNum);
            ServerThread st2 = new ServerThread(client2, secretNum);
            st1.start();
            st2.start();
        } catch (IOException e) {
            System.err.println("Failed to start server threads : " + e.getMessage());
        }

        try {
            ss.close();
        } catch (IOException e) {
            System.err.println("Failed to close server socket : " + e.getMessage());
        }
    }
}
