import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
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
        DatagramSocket clientSocketIP = new DatagramSocket();

        InetAddress serverAddress = InetAddress.getByName("10.10.132.109");
        int serverPort = 6790;
        System.out.println("1serveraddress");

        BufferedReader inFromUserIp = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("2buffered infromuserIP");
        System.out.println("Skriv navnet på den du ønsker at chatte med: ");
        navn = inFromUserIp.readLine();
        System.out.println("3navn");

        byte[] sendData = navn.getBytes();
        System.out.println("4getbytes");
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
        System.out.println("5sendpacket");
        clientSocketIP.send(sendPacket);
        System.out.println("6packet sendt");

        byte[] receiveData = new byte[1024];
        System.out.println("7recive data");
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        System.out.println("8create datagrampacket");
        clientSocketIP.receive(receivePacket);
        System.out.println("clientsocket recieve");

        ip = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("22ip lavet");
        System.out.println("22IP-adresse for " + navn + " er: " + ip);

        clientSocketIP.close();
        System.out.println("23clientsocket lukket");
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
