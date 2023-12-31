import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer {

    public static void main(String[] args) throws Exception {
        String clientSentence;
        String serverInput;

        ServerSocket welcomeSocket = new ServerSocket(6789);

        System.out.println("Venter på klientforbindelse...");
        Socket connectionSocket = welcomeSocket.accept();
        System.out.println("Klient forbundet.");
        SkriveTraad skriveTraad = new SkriveTraad(connectionSocket);
        ThreadLæse threadLæse = new ThreadLæse(connectionSocket);

        //BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
       // DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

        Scanner scanner = new Scanner(System.in);
        System.out.println("En anden klient forsøger at sende en besked. Vil du chatte med dem? (ja/nej)");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("nej")) {
            System.out.println("Okay, du vil ikke chatte lige nu. Lukker forbindelsen.");
            connectionSocket.close();
        } else if (response.equalsIgnoreCase("ja")) {

                threadLæse.start();
                skriveTraad.start();

            /**
             while (true) {

             System.out.print("Skriv en besked til klienten: ");
             serverInput = scanner.nextLine();
             outToClient.writeBytes(serverInput + '\n');

             clientSentence = inFromClient.readLine();
             System.out.println("FROM OTHER CLIENT: " + clientSentence);

             }
             } else {
             System.out.println("Svar med 'ja' eller 'nej'.");
             }

*/
            welcomeSocket.close();
        }

    }
}
