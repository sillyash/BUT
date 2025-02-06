import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;

class ServerThread extends Thread {
    protected Socket socket;
    protected PrintWriter writeData;
    protected BufferedReader readData;
    protected Vector<Integer> numbers;

    public ServerThread(Socket s, Vector<Integer> v) throws IOException {
        this.socket = s;
        this.numbers = v;
        this.writeData = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.readData = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        System.out.println("Waiting for numbers...");

        for (int i=0; i < ClientThread.MAX_NUMBERS; i++) {
            String str = "";

            try {
                str = readData.readLine().trim();
            } catch (IOException e) {
                System.err.println("IOException : " + e.getMessage());
            }

            int num = Integer.parseInt(str);
            System.out.println("Received " + num + ".");
            numbers.add(num);
        }

        System.out.print("Sending " + Server.END_MSG + " to client... ");
        writeData.println(Server.END_MSG);
        writeData.flush();
        System.out.println("Done !");
    }
}
