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

        Socket clientSocket = new Socket("localhost", 6789);
        SkriveTraad skriveTraad = new SkriveTraad(clientSocket);
        ThreadLæse threadLæse = new ThreadLæse();
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        while (true){
            skriveTraad.start();
            threadLæse.start();
        }
        /**
        while (true) {
			// Modtag og udskriv svar fra serveren
			//sentenceFromOtherClient = inFromServer.readLine();
			//System.out.println("FROM SERVER: " + sentenceFromOtherClient);
            threadLæse.start();

            // Læs besked fra brugeren
            System.out.print("Skriv en besked til serveren: ");

            sentence = inFromUser.readLine();

            // Send besked til serveren
            outToServer.writeBytes(sentence + '\n');
         **/

        }
        //clientSocket.close();

    }
