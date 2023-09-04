import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadLæse extends Thread {
    String sentenceFromOtherClient;
    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in));

    public ThreadLæse() {

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
