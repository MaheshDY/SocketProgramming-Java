import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientProgram {
  public static void main(String[] args) {
    String serverAddress = "localhost";
    int port = 5050;

    try (Socket socket = new Socket(serverAddress, port)) {
      System.out.println("Connected to " + socket.getRemoteSocketAddress());

      // Setting up input and output streams
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

      // Read the server's welcome message
      System.out.println("Server: " + in.readLine());

      String userInput;

      // Loop to send user input to the server
      while(true) {
        System.out.println("You: ");
        userInput = consoleInput.readLine(); // Read input from user

        out.println(userInput); // Send input to server

        if("bye".equalsIgnoreCase(userInput)) {
          System.out.println("Connection closed by client");
          break;
        }
        // Read server response
        System.out.println("Server: " + in.readLine());
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}