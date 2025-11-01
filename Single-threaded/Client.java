import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public void run() throws IOException {
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        try (Socket socket = new Socket(address, port)) {
            PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            toServer.println("This is from client ");

            String messageFromSocket = fromSocket.readLine();
            System.out.println("Message from Socket : " + messageFromSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}