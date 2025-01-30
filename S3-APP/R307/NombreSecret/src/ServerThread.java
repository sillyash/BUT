import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServerThread extends Thread
{
    protected Socket socket;
    protected PrintWriter writeData;
    protected BufferedReader readData;
    protected int secretNum;

    public ServerThread(Socket s, int secretNum) throws IOException {
        this.socket = s;
        this.secretNum = secretNum;
        this.readData = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writeData = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        writeData.println("GO");
        writeData.flush();

        String read = "";
        int guessedNum = Integer.MIN_VALUE;

        try {
            do {
                read = readData.readLine().strip();
                try {
                    guessedNum = Integer.parseInt(read);
                } catch (NumberFormatException e) {
                    System.err.println("Received \"" + read + "\" : not a number.");
                    System.err.println(e.getMessage());
                }
                writeData.println("Try again!");
                writeData.flush();
            } while (guessedNum != secretNum);
        } catch (IOException e) {
            System.err.println("Failed to read buffer : " + e.getMessage());
        }

        writeData.println("Congratulations, you found the secret number : " + secretNum + "!");
        writeData.flush();

        try {
            readData.close();
            writeData.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Failed to close buffer/connection : " + e.getMessage());
        }
    }
}
