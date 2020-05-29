package files;

import java.io.*;
import java.net.Socket;

public class GameClient {
    private static final int MYPORT = 7000;
    private static final String MYHOST = "127.0.0.1";

    static DataInputStream input = null;
    static DataOutputStream output = null;
    static Socket socket = null;
    static BufferedReader keyboard;


    public static void main(String[] args) throws IOException {
        initializeConnection();
        while (true){
            String currentLine = keyboard.readLine();
            if (currentLine.equals("exit")) {
                GameClientUtils.clientWrite(output, currentLine);
                System.out.println(GameClientUtils.clientRead(input));
                break;
            }
            GameClientUtils.clientWrite(output, currentLine);
            System.out.println(GameClientUtils.clientRead(input));
        }
    }

    private static void initializeConnection() {
        try {
            socket = new Socket(MYHOST, MYPORT);
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Successful connection...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
