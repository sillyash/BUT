import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread
{
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
        System.out.println(
                "Secret number game : find the number between " +
                Server.MIN_NUM + " and " + Server.MAX_NUM
        );

        Scanner sc = new Scanner(System.in);
        String response = "";

        System.out.println("Waiting for another player... ");

        try {
            do {
                response = readData.readLine().strip();
            } while (!response.equals("GO"));

            System.out.println("Found!");

            do {
                System.out.print(
                        "Please input a number between " + Server.MIN_NUM +
                         " and " + Server.MAX_NUM + " >>> "
                );

                int guessedNum = sc.nextInt();
                writeData.println(guessedNum);
                writeData.flush();

                response = readData.readLine().strip();
            } while (response.equals("Try again!"));
        } catch (IOException e) {
            System.err.println("Failed to read buffer : " + e.getMessage());
        }

        try {
            sc.close();
            writeData.close();
            readData.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Failed to close buffer/connection : " + e.getMessage());
        }
    }
}
