package Server;


import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ServerStrategyGenerateMaze implements IServerStrategy {


    /**
     * This function will apply a server strategy
     *
     * @param inFromClient -an InputStream instance we will read the data from
     * @param outToClient  -an OutputStream instance we will write the data to
     */
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(inFromClient);
            ObjectOutputStream outputStream = new ObjectOutputStream(outToClient);
            int[] Size_Coordinates = (int[])inputStream.readObject();


        }catch (Exception ignored)
        {

        }
    }
}
