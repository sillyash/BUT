import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client
{
    public static final String LOCALHOST = "127.0.0.1";
    public static final int DEFAULT_PORT = Server.DEFAULT_PORT;

    public static String readIPv4(Scanner sc) {
        System.out.print("Address of server to join (enter for localhost) >>> ");
        String read = sc.nextLine().replaceAll("\\s", "");

        if (read.isEmpty()) return LOCALHOST;
        String regex = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(read);

        if (matcher.matches()) return read;
        return null;
    }

    private static int readPort(Scanner sc) {
        System.out.print("Port of server to join (enter for default " + DEFAULT_PORT + ") >>> ");
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

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String serverIP = "";
        boolean ipOK = false;

        while (!ipOK) {
            serverIP = readIPv4(sc);
            if (serverIP != null) ipOK = true;
        }

        int serverPort = 0;
        boolean portOK = false;

        while (!portOK) {
            serverPort = readPort(sc);
            if (serverPort != -1) portOK = true;
        }

        Socket s;
        try {
            s = new Socket(serverIP, serverPort);
        } catch (UnknownHostException e) {
            System.err.print("Unknown host : " + serverIP + ":" + serverPort);
            System.err.println(e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("IOException : " + e.getMessage());
            return;
        }

        try {
            ClientThread c = new ClientThread(s);
            c.start();
        } catch (IOException e) {
            System.err.println("Failed to start client thread : " + e.getMessage());
        }
    }
}