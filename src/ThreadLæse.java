import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadLæse extends Thread {
    String sentenceFromOtherClient;

    Socket socket;
    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in));

    public ThreadLæse(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        while (true) {
            try {
                sentenceFromOtherClient = inFromServer.readLine();
                System.out.println("From other client: " + sentenceFromOtherClient);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
