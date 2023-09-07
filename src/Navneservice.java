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
        byte[] receiveData = new byte[1024];

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);

        String navn = new String(receivePacket.getData(), 0, receivePacket.getLength());
        ip = navneservice.getIp(navn);

        InetAddress clientAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();
        String response = ip;

        byte[] sendData = response.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
        serverSocket.send(sendPacket);

        serverSocket.close();
    }
}
