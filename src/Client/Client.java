package Client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;

public class Client {
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy clientStrategy;

    public Client(InetAddress serverIP, int serverPort, IClientStrategy clientStrategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.clientStrategy = clientStrategy;
    }
    public void communicateWithServer() {

    }

}
