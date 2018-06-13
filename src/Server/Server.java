package Server;

import algorithms.mazeGenerators.GeneratorFactory;
import algorithms.search.SearchAlgorithmFactory;

import java.io.*;
import java.net.*;
import java.nio.file.Paths;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.Properties;


/**
 * This class will represent the server. Server will treat in client requests.
 */
public class Server {
    private int port;//The port
    private int listeningInterval;//The listening intervals
    private IServerStrategy serverStrategy;//The server strategy
    private volatile boolean stop;//The variable that is responsible to stop the server


    /**
     * c'tor
     *
     * @param port              - The given port
     * @param listeningInterval - The listening intervals
     * @param serverStrategy    - The server strategy
     */
    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
    }

    /**
     * Create new thread that will run rnuServer.
     */
    public void start() {
        //Runs the server
        new Thread(this::runServer).start();
    }

    /**
     * This function is responsible to get request from client and treat her.
     */
    private void runServer() {
        //Initializing the threadPool

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        threadPoolExecutor.setCorePoolSize((Integer)Configurations.getProperty(Configurations.PROPERTIES.NUM_OF_THREADS));
//        threadPoolExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors() * 2);
        try {
            //Creating the server socket
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(listeningInterval);
            //As long as we don't tell the server to stop
            while (!stop) {
                try {
                    //The clientSocket of the client that is connecting to the server
                    Socket clientSocket = server.accept();
                    //Executing the client handler (adding the runnable to the threadPool and executing it)
                    threadPoolExecutor.execute(new ParallelServer(clientSocket, this.serverStrategy));
                } catch (SocketTimeoutException e) {
                    e.getStackTrace();
                }
            }
            //Close the server
            server.close();
            threadPoolExecutor.shutdown();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }


    /**
     * This function will stop the server's running.
     * stop is common to all thread and therefore all the thread will stop.
     */
    public void stop() {

        stop = true;
    }

    /**
     * This nested class implement Runnable.
     * We use this class to allow threadpool run this fonction in this class.
     */
    class ParallelServer implements Runnable {

        private Socket clientSocket;//The serverSocket
        private IServerStrategy serverStrategy;//The strategy

        /**
         * c'tor
         *
         * @param socket    - The given server socket
         * @param strategy- The given strategy
         */
        public ParallelServer(Socket socket, IServerStrategy strategy) {
            clientSocket = socket;
            serverStrategy = strategy;
        }

        /**
         * This function will handle the client using the given strategy
         *
         * @param clientSocket - The clientSocket
         */

        private void handleClient(Socket clientSocket) {
            try {
                //Implementing the strategy
                serverStrategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
                //Closing the streams of the client
                try {
                    clientSocket.getInputStream().close();
                } catch (Exception e) {
                }
                try {
                    clientSocket.getOutputStream().close();
                } catch (Exception e) {
                }
                try {
                    clientSocket.close();
                } catch (Exception e) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Summoning the handleClient function
         */
        public void run() {
            handleClient(clientSocket);
        }
    }

    /**
     * This class will represent the work the server can do with the configuration file
     */
    public static class Configurations {
        private static String PATH = "Resources/config.properties";
        private static Properties prop = new Properties();

        public enum PROPERTIES {
            NUM_OF_THREADS, SOLVE_ALGORITHM, MAZE_GENERATION_ALGORITHM;

            private static Object getObject(PROPERTIES p, String value) {
                switch (p) {
                    case NUM_OF_THREADS:
                        if (Integer.parseInt(value)>0)
                            return Integer.parseInt(value);
                        else
                            throw new IllegalArgumentException("Illegal property");
                    case MAZE_GENERATION_ALGORITHM:
                        return GeneratorFactory.createGenerator(value);
                    case SOLVE_ALGORITHM:
                        return SearchAlgorithmFactory.createAlgorithm(value);
                    default:
                        throw new IllegalArgumentException("Illegal property");
                }
            }
        }//static

        private Configurations() {
        }

        /**
         * This function will able the server to set/enter a property in/to the configuration file
         *
         * @param key   - the key in the updated/new property
         * @param value - the value in the updated/new property
         */
        public static void setProperty(PROPERTIES key, String value) {
            OutputStream output = null;

            try {
                output = new FileOutputStream(PATH);

                // set the properties value
                try {
                    PROPERTIES.getObject(key, value);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                prop.setProperty(key.name(), value);

                // save properties to project root folder
                prop.store(output, null);

            } catch (IOException io) {
                io.printStackTrace();
            } finally { /*Safe close of the streams */
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * @param key - the key in the property pair
         * @return the value(as Object) of the property defined by the given key
         */
        public static Object getProperty(PROPERTIES key) {
            InputStream input = null;
            Object value = null;
            try {
                input = new FileInputStream(PATH);

                //load a properties file from class path, inside static method
                prop.load(input);

                //get the property value and print it out
                value = PROPERTIES.getObject(key,prop.getProperty(key.name()));

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally { /*Safe close of the stream */
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return value;
        }

    }
}