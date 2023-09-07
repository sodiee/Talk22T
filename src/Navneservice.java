import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;

public class Navneservice {
    private HashMap<String, String> map;

    public Navneservice() {
        initializeMap();
    }

    public void initializeMap() {
        map = new HashMap<>();
        map.put("Mikkel", "10.10.139.117");
        map.put("Tayyip", "10.10.139.141");
        map.put("Lucas", "10.10.132.109");
        map.put("Mathias", "10.10.139.153");
    }

    public void printMap() {
        System.out.println("Channels available: ");
        System.out.println(map.entrySet());
    }

    public String getIp(String s) {
        return map.get(s);
    }

    public static void main(String[] args) throws IOException {
        String ip;
        Navneservice navneservice = new Navneservice();
/*
        //TCP
        ServerSocket welcomeSocket = new ServerSocket(6790);
        Socket connectionSocket = welcomeSocket.accept();


        BufferedReader bufferedReaderNavn = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        String navn = bufferedReaderNavn.readLine();


        ip = navneservice.getIp(navn);

        DataOutputStream dataOutputStream = new DataOutputStream(connectionSocket.getOutputStream());
        dataOutputStream.writeBytes(ip + '\n');
        */
        //UDP
        DatagramSocket serverSocket = new DatagramSocket(6790);
        System.out.println("9serversocket lavet");
        byte[] receiveData = new byte[1024];
        System.out.println("10recievedata [] lavet");

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        System.out.println("11datagram receivepacket lavet");
        serverSocket.receive(receivePacket);
        System.out.println("12datapakke sendt");

        String navn = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("13navn modtaget");
        ip = navneservice.getIp(navn);
        System.out.println("14ip lavet");

        InetAddress clientAddress = receivePacket.getAddress();
        System.out.println("15få inetadresse");
        int clientPort = receivePacket.getPort();
        System.out.println("16get port");
        String response = ip;
        System.out.println("17repsone = ip");

        byte[] sendData = response.getBytes();
        System.out.println("18lav [] senddata");
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
        System.out.println("19create sendpacket");
        serverSocket.send(sendPacket);
        System.out.println("20send sendpacket");

        serverSocket.close();
        System.out.println("21server close");
    }
}
