package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * This interface must be implement in any class which want to be Server.
 */
public interface IServerStrategy {
    /**
     * This function will apply a server strategy
     * @param inFromClient-an InputStream instance we will read the data from
     * @param outToClient-an OutputStream instance we will write the data to
     */
    void applyStrategy(InputStream inFromClient, OutputStream outToClient);
}
