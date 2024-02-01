import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;



/**
 * The Connection class implements a simple TCP server that listens on a specified port
 * and handles incoming client connections. Each client connection is managed in a separate thread
 * to allow concurrent handling of multiple clients. The server echoes back any data received from the client.
 * 
 * The server runs indefinitely, listening for and accepting new connections, until externally terminated.
 */
public class Connection {
    
    public static void main(String[] args) throws IOException {
        
        //Create ServerSocket to listen to a given port 
        int port = 6789;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("The server is listening to PORT " + port);

        //For loop to wait and listen to socket for a request
        try {
            //While Loop runs perpetually 
            while(true) {
                System.out.println("WAITING FOR CONNECTION...");
                //Generates the socket we will be sending data to
                Socket clientSocket = serverSocket.accept();
                //Prints the connected ip address
                System.out.println("Connection ACCEPTED from..." + clientSocket.getInetAddress());

                //Creates a new thread, and passes it a new "runnable" object with our clientsocket 
                //Passing the client connection into a thread allows our program to handle multiple connections concurrently
                new Thread(new HandleConnection(clientSocket)).start();
            }
        //"Closes" the connection, however our current code doesn't reach this block -- Future update    
        } finally {
            serverSocket.close();
        }
        
    }    
}

/**
 * The HandleConnection class is responsible for handling individual client connections to the server.
 * It implements the Runnable interface, allowing each connection to be processed in a separate thread.
 * This class manages reading data from the client, echoing it back, and closing the connection when done.
 */
class HandleConnection implements Runnable {
    //The socket we want to communicate with
    private Socket clientSocket; 

    //Constructor
    public HandleConnection(Socket sock) {
        this.clientSocket = sock;
    }

    //Derived from runnable (Allows us to assign a "Task" and have it run separetely in different threads)
    public void run() {
        //Input stream concerts byte stream to character stream
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //PrintWriter translates our return data for the OutputStream
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            //Current Line taken from input stream
            String inputLine;
            //While their is data left (Inputline isn't null)
            while ((inputLine = in.readLine()) != null) {
                //Print to console 
                System.out.println("RECEIVED: " + inputLine);
                //Send to the output stream for the client to recieve
                out.println(inputLine);
            }
        //Catch IO error if there is a problem intializing the readers
        } catch (IOException e) {
            System.out.println("Exception in ConnectionHandler: " + e.getMessage());
        //Try to close the client socket
        } finally {
            try {
                clientSocket.close();
            //Print the IO error if we can't close it
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }   
}


