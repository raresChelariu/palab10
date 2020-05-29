package files;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GameClientUtils {
    public static void clientWrite(DataOutputStream out, String sir) throws IOException {
        System.out.println("From client : " + sir);
        out.writeUTF(sir);
        out.flush();
    }
    public static String clientRead(DataInputStream in) throws IOException {
        String sir = in.readUTF();
        System.out.println("From server : " + sir);
        return sir;
    }
}
