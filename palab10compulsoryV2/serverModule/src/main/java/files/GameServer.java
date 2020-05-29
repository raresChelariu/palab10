package files;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;

public class GameServer {
    private static final int PORT = 7000;
    private static ServerSocket server;

    private static void initServer() {
        try {
            server = new ServerSocket(PORT);
            System.out.println("Now listening for connections ...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        initServer();
        try {
            while (true) {
                new AppClient(server.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
