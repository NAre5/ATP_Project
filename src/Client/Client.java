package Client;

import java.net.InetAddress;
import java.net.Socket;

/**
 * This class will represent a Client. Client will request requests from the Server.
 */
public class Client {
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy clientStrategy;

    /**
     * c'tor
     *
     * @param serverIP       - the server IP
     * @param serverPort     - the server Port
     * @param clientStrategy - the Strategy for the Client
     */
    public Client(InetAddress serverIP, int serverPort, IClientStrategy clientStrategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.clientStrategy = clientStrategy;
    }

    /**
     * establish the communication with the server
     */
    public void communicateWithServer() {
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            System.out.println(String.format("Client is connected to server (IP: %s, port: %s)", serverIP, serverPort));
            clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
