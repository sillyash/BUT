import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ServerThread extends Thread {
    protected Socket socket;
    protected PrintWriter writeData;
    protected BufferedReader readData;
    protected ArrayList<String[]> clientInfos = new ArrayList<>();

    public ServerThread(Socket s, ArrayList<String[]> c) throws IOException {
        this.socket = s;
        this.clientInfos = c;
        this.writeData = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.readData = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static boolean isIPv4(String str) {
        String read = str.replaceAll("\\s", "");
        if (read.isEmpty()) return false;

        String regex = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(read);

        if (matcher.matches()) return true;
        return false;
    }

    @Override
    public void run() {
        String hostName = "";
        String ipAddr = "";
        System.out.print("Waiting for client data... ");
        try {
            hostName = readData.readLine();
            ipAddr = readData.readLine();
        } catch (IOException e) {
            System.err.println("IOException : " + e.getMessage());
        }

        // Trim data
        System.out.println("Received !");
        hostName = hostName.trim();
        ipAddr = ipAddr.trim();

        // Check values
        if (!isIPv4(ipAddr)) {
            writeData.println("Error : address isn't IPv4 : " + ipAddr);
            writeData.flush();
        }

        // Store info
        String[] infos = {hostName, ipAddr};
        this.clientInfos.add(infos);

        // Send ACK to Client
        System.out.print("Sending " + Server.ACK_MESSAGE + " to client... ");
        writeData.println(Server.ACK_MESSAGE);
        writeData.flush();

        // Exit
        System.out.println("Done !");
    }
}