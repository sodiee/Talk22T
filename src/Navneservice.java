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
        String navn;
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
        DatagramSocket serverDatagramSocket = new DatagramSocket(6790);
        byte[] byteArr = new byte[1024];
        DatagramPacket serverDatagramPacket = new DatagramPacket(byteArr, byteArr.length);
        serverDatagramSocket.receive(serverDatagramPacket);
        System.out.println("modtaget");

        navn = new String(serverDatagramPacket.getData(), 0, serverDatagramPacket.getLength()).trim();

        InetAddress clientAdress = serverDatagramPacket.getAddress();
        int clientPort = serverDatagramPacket.getPort();

        System.out.println("Fra klienten: " + navn);

        ip = navneservice.getIp(navn);

        byte[] sendData = ip.getBytes();
        DatagramPacket sendPakke = new DatagramPacket(sendData, sendData.length, clientAdress, clientPort);
        serverDatagramSocket.send(sendPakke);
        System.out.println("sendt tilbage");
    }
}
