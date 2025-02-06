import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

class ClientThread extends Thread {
    protected Socket socket;
    protected PrintWriter writeData;
    protected BufferedReader readData;

    public ClientThread(Socket s) throws IOException {
        this.socket = s;
        this.writeData = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.readData = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        System.out.println("Client thread started !");
        try {
            // Send data to Server
            InetAddress localMachine = InetAddress.getLocalHost();
            writeData.println(localMachine.getHostName());
            writeData.flush();

            writeData.println(localMachine.getHostAddress());
            writeData.flush();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }

        String serverMessage = "";
        try {
            serverMessage = readData.readLine();
        } catch (IOException e) {
            System.err.println("IOException : " + e.getMessage());
        }
        // Trim data
        serverMessage = serverMessage.trim();

        // Check message
        if (serverMessage.equals(Server.ACK_MESSAGE)) {
            System.out.println("Server acknowledged client : " + serverMessage);
            System.out.print("Sending " + Server.ACK_MESSAGE + " to server... ");

            writeData.println(Server.ACK_MESSAGE);
            writeData.flush();

            System.out.println("Done !");
        } else {
            System.out.println("Server sent message : " + serverMessage);
        }
    }
}