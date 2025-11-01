import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run() throws IOException {
        int port = 8010;

        try (ServerSocket socket = new ServerSocket(port)) {

            System.out.println("Server is listening on port : " + port);
            while (true) {
                try (Socket acceptedConnection = socket.accept();) {

                    PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                    BufferedReader fromClient = new BufferedReader(
                            new InputStreamReader(acceptedConnection.getInputStream()));

                    System.out.println("Connection is accepcted on : " + acceptedConnection.getRemoteSocketAddress());

                    toClient.println("Hello form the server: ");

                    String messageFromClient = fromClient.readLine();
                    System.out.println("Message from client : " + messageFromClient);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
