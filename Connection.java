import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

public class Connection {
    
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            serverSocket.close();
        } catch (IOException e) {
            System.err.print("IO EXCEPTION :" + e.getMessage());
            e.printStackTrace();
        }
        
    }    
}
