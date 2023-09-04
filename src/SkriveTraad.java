import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SkriveTraad extends Thread{
    String sentence;
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    Socket clientSocket;

    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

    public SkriveTraad(Socket clientSocket) throws IOException {
        clientSocket = this.clientSocket;
    }

    @Override
    public void run() {
        System.out.print("Skriv en besked til serveren: ");

        try {
            sentence = inFromUser.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Send besked til serveren
        try {
            outToServer.writeBytes(sentence + '\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
