import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadLæse extends Thread {
    String sentenceFromOtherClient;

    Socket socket;
    BufferedReader inFromServer;

    public ThreadLæse(Socket clientSocket) {
        this.socket = clientSocket;
        try {
            this.inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        while (true) {
            try {
                sentenceFromOtherClient = inFromServer.readLine();
                if(sentenceFromOtherClient.equals("null")) {
                    System.out.println("\nFrom other client: " + sentenceFromOtherClient);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
