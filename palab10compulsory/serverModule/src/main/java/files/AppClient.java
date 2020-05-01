package files;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class AppClient extends Thread {
    Socket clientConn;
    private DataInputStream in;
    private DataOutputStream out;
    public AppClient(Socket clientConn) {
        this.clientConn = clientConn;
    }
    public static String receiveFromClient(DataInputStream in) throws IOException{
        String sir = in.readUTF();
        System.out.println("From client : " + sir);
        return sir;
    }
    public static void sendToClient(DataOutputStream out, String sir) throws IOException {
        out.writeUTF(sir);
        out.flush();
        System.out.println("To client:" + sir);
    }
    @Override
    public void run() {
        initStreams();
        try {
            while (true) {
                String clientMsg = receiveFromClient(in);
                if (clientMsg.toLowerCase().equals("stop")) {
                    sendToClient(out, "Server stopped");
                    break;
                }
                sendToClient(out, "Server received the request : " + clientMsg);
            }
            clientConn.close();
        }
        catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initStreams() {
        try {
            in = new DataInputStream(new BufferedInputStream(clientConn.getInputStream()));
            out = new DataOutputStream(new BufferedOutputStream(clientConn.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
