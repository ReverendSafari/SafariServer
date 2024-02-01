import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class Connection {
    
    public static void main(String[] args) throws IOException {
        
        //Create ServerSocket to listen to a given port 
        int port = 6789;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("The server is listening to PORT " + port);

        //For loop to wait and listen to socket for a request
        try {
            while(true) {
                System.out.println("WAITING FOR CONNECTION...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection ACCEPTED from..." + clientSocket.getInetAddress());

                new Thread(new HandleConnection(clientSocket)).start();
            }
        } finally {
            serverSocket.close();
        }
        
    }    
}

class HandleConnection implements Runnable {
    private Socket clientSocket; 

    public HandleConnection(Socket sock) {
        this.clientSocket = sock;
    }
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("RECEIVED: " + inputLine);
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception in ConnectionHandler: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }   
}


