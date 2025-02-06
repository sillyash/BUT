import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Server
{
    public static final int DEFAULT_PORT = 8765;
    public static final int MAX_CLIENTS = 3;
    public static final String START_MSG = "start";
    public static final String END_MSG = "end";

    protected static ArrayList<ServerThread> threads = new ArrayList<>();
    protected static Vector<Integer> numbers = new Vector<>();

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

    public static void main(String[] args) throws InterruptedException {
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
        int clients = 0;

        while (clients < MAX_CLIENTS) {
            Socket client;
            try {
                client = ss.accept();
                System.out.println("Client request accepted !");
            } catch (IOException e) {
                System.err.println("IOException : " + e.getMessage());
                return;
            }

            ServerThread st = null;
            try {
                st = new ServerThread(client, numbers);
                st.start();
            } catch (IOException e) {
                System.err.println("Failed to start server threads : " + e.getMessage());
            }

            // Increment clients
            threads.add(st);
            clients++;
        }

        System.out.println("Got " + clients + " clients!");

        // Wait for all threads
        for (ServerThread thread : threads) {
            thread.writeData.println(START_MSG);
            thread.writeData.flush();
        }

        for (ServerThread thread : threads) {
            thread.join();
        }

        try {
            ss.close();
        } catch (IOException e) {
            System.err.println("Failed to close server socket : " + e.getMessage());
        }

        System.out.println("\nData :");
        for (int num : numbers) {
            String str = "";
            if (num > 0) str = " ";
            System.out.println(str + num);
        }
    }
}