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
        ServerSocket welcomSocket = new ServerSocket(6789);

        Socket connectionSocket = welcomSocket.accept();
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

        Scanner scanner = new Scanner(System.in);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        while (true) {

            System.out.println("En anden klient forsøger at koble til dit chatrum. Vil du chatte med dem?");
            if (scanner.nextLine().equals("no")) {
                System.out.println("Okay, du vil ikke chatte lige nu.");
                //welcomSocket.close();
                break;
            } else {
                //welcomSocket.accept();
                clientSentence = inFromClient.readLine();
                System.out.println("FROM OTHER CLIENT: " + clientSentence);
                serverInput = bufferedReader.readLine();
                outToClient.writeBytes(serverInput);
            }
        }

    }

}
