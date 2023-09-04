import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class TCPClient {

    public static void main(String[] args) throws Exception, IOException {

        String sentence;
        String sentenceFromOtherClient;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("10.10.139.114", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (true) {
			// Modtag og udskriv svar fra serveren
			sentenceFromOtherClient = inFromServer.readLine();
			System.out.println("FROM SERVER: " + sentenceFromOtherClient);

            // Læs besked fra brugeren
            System.out.print("Skriv en besked til serveren: ");
            sentence = inFromUser.readLine();

            // Send besked til serveren
            outToServer.writeBytes(sentence + '\n');
//hej

        }
        //clientSocket.close();

    }

}
