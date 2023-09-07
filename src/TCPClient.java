import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;


public class TCPClient {

    public static void main(String[] args) throws Exception, IOException {
        String sentence;
        String sentenceFromOtherClient;
        String ip;
        String navn;

        /*
        //TCP
        //IP del
        Socket clientSocketIp = new Socket("10.10.132.109", 6790);
        BufferedReader inFromUserIp = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Skriv navnet på den du ønsker at chatte med: ");
        navn = inFromUserIp.readLine();

        DataOutputStream dataOutputStreamIp = new DataOutputStream(clientSocketIp.getOutputStream());
        dataOutputStreamIp.writeBytes(navn + '\n');

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocketIp.getInputStream()));
        ip = inFromServer.readLine();

        System.out.println("IP-adresse for " + navn + " er: " + ip);

        clientSocketIp.close();
*/
        //UDP
        InetAddress inetAddress = InetAddress.getByName("10.10.139.153");
        DatagramSocket clientDatagramSocket = new DatagramSocket(6790);
        byte[] byteArr = new byte[1024];

        DatagramPacket clientDatagramPacket = new DatagramPacket(byteArr, byteArr.length, inetAddress, 6790);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Skriv navn på den du ønsker at chatte med: ");
        navn = bufferedReader.readLine();
        byteArr = navn.getBytes();
        clientDatagramPacket.setData(byteArr);
        clientDatagramSocket.send(clientDatagramPacket);
        System.out.println("Sendt");

        byte[] ipByteArr = new byte[1024];
        DatagramPacket receivedIpPacket = new DatagramPacket(ipByteArr, ipByteArr.length);
        clientDatagramSocket.receive(receivedIpPacket);

        ip = new String(receivedIpPacket.getData(), 0, receivedIpPacket.getLength()).trim();
        System.out.println("Ip for: " + navn + "er: " + ip);

        clientDatagramSocket.close();
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
