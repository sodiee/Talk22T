import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SkriveTraad extends Thread{
    String sentence;
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    Socket clientSocket;

    DataOutputStream outToServer;

    public SkriveTraad(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.outToServer = new DataOutputStream(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        System.out.print("Skriv en besked til serveren: ");
        while (true) {



            try {
                sentence = inFromUser.readLine();
                outToServer.writeBytes(sentence + '\n');
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Send besked til serveren



        }
    }
}
