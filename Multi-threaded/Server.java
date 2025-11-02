import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {
    public Consumer<Socket> getConsumer() {
        return (clientSocket) -> {
            try {
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
                toClient.println("Hello from the Server");
                toClient.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };
    }

    public static void main(String[] args) throws IOException {
        int port = 8010;
        Server server = new Server();
        try (ServerSocket socket = new ServerSocket(port)) {
            System.out.println("Server is listening on port :" + port);

            while (true) {

                Socket acceptedSocket = socket.accept();
                Thread thread = new Thread(() -> server.getConsumer().accept(acceptedSocket));
                thread.start();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
