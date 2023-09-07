import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;


public class TCPClient {

    public static void main(String[] args) throws Exception, IOException {
        String sentence;
        String sentenceFromOtherClient;
        String ip;
        String navn;

        //IP del
        Socket clientSocketIp = new Socket("10.10.139.117", 6790);
        BufferedReader inFromUserIp = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Skriv navnet på den du ønsker at chatte med: ");
        navn = inFromUserIp.readLine();

        DataOutputStream dataOutputStreamIp = new DataOutputStream(clientSocketIp.getOutputStream());
        System.out.println("client dataoutput");
        dataOutputStreamIp.writeBytes(navn + '\n');
        System.out.println("client writebytes");

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocketIp.getInputStream()));
        System.out.println("client infromserver");
        ip = inFromServer.readLine();
        System.out.println("client infromserver.readline");

        System.out.println("IP-adresse for " + navn + " er: " + ip);

        clientSocketIp.close();

        //Chat del
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket(ip, 6789);
        SkriveTraad skriveTraad = new SkriveTraad(clientSocket);
        ThreadLæse threadLæse = new ThreadLæse(clientSocket);
        //DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        //BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            skriveTraad.start();
            threadLæse.start();
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
