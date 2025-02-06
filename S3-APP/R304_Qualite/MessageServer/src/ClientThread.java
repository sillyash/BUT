import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

class ClientThread extends Thread {
    protected Socket socket;
    protected PrintWriter writeData;
    protected BufferedReader readData;
    protected ArrayList<Integer> numbers = new ArrayList<>();
    protected static final int MAX_NUMBERS = 10;

    public ClientThread(Socket s) throws IOException {
        this.socket = s;
        this.writeData = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.readData = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        System.out.print("Waiting for start message... ");
        String read = "";
        try {
            read = readData.readLine().trim();
        } catch (IOException e) {
            System.err.println("IOException : " + e.getMessage());
        }
        if (read.equals(Server.START_MSG)) {
            System.out.println("Done !");
        } else {
            System.out.println("Received : " + read);
            return;
        }

        System.out.println("Generating and transmitting numbers...");
        Random rand = new Random();

        for (int i=0; i<MAX_NUMBERS; i++) {
            int num = rand.nextInt();
            numbers.add(num);

            System.out.print("Sending " + num + "... ");
            writeData.println(num);
            writeData.flush();
            System.out.println("Done !");
        }

        String msg = "";
        System.out.print("Waiting for server... ");
        try {
            msg = readData.readLine().trim();
        } catch (IOException e) {
            System.err.println("IOException : " + e.getMessage());
        }

        if (msg.equals(Server.END_MSG)) {
            System.out.println("Done !");
        } else {
            System.out.println("Received : " + msg);
            return;
        }
    }
}