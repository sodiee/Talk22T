import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
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
        System.out.println("Channels availabe: ");
        System.out.println(map.entrySet());
    }

    public String getIp(String s) {
        return map.get(s);
    }

    public static void main(String[] args) throws IOException {
        String ip;
        Navneservice navneservice = new Navneservice();

        ServerSocket welcomeSocket = new ServerSocket(6790);
        Socket connectionSocket = welcomeSocket.accept();


        BufferedReader bufferedReaderNavn = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        String navn = bufferedReaderNavn.readLine();


        ip = navneservice.getIp(navn);

        DataOutputStream dataOutputStream = new DataOutputStream(connectionSocket.getOutputStream());
        dataOutputStream.writeBytes(ip + '\n');
    }
}
