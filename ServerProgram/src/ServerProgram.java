import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProgram {
  public static void main(String[] args) {
    int port = 5050;

    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("Server started and waiting for clients...");

      // Accept client connection
      Socket socket = serverSocket.accept();
      System.out.println("Client connected" + socket.getInetAddress());

      // Setting up input and output streams to receive and send data
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

      // Send a welcome message to the client
      out.println("Welcome to the server! Type 'bye' to exit");

      String clientMessage;

      // Read client messages until "bye" is received
      while ((clientMessage = in.readLine()) != null) {
        System.out.println("client says: " + clientMessage);

        // Respond to client
        out.println("server received: " + clientMessage);

        if ("bye".equals(clientMessage)) {
          System.out.println("Client disconnected");
          break;
        }
      }
      // Close the socket and clean up
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}