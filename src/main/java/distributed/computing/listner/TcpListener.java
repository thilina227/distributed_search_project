package distributed.computing.listner;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dev on 11/12/16.
 *
 * This class is acting as a TCP server to receive messages from Bootstrap server
 */
public class TcpListener extends Listener {

    private static final int THREAD_POOL_SIZE = 10;
    private static Listener instance;

    //Singleton
    private TcpListener() {
    }

    @Override
    public void initListener(int port) {
        System.out.println("Starting TCP Listener...");

        final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                        clientProcessingPool.submit(new ClientTask(clientSocket));
                    }

                    //TODO graceful shutdown
                } catch (IOException e) {
                    //TODO log
                    e.printStackTrace();
                }
            }
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
        System.out.println("TCP Listener started!");
    }

    @Override
    public String processMessage(String message) {
        //TODO implement
        return "test";
    }

    /**
     * Get current instance
     *
     * @return TcpListener instance
     * */
    public static Listener getInstance() {
        if (instance == null) {
            instance = new TcpListener();
        }
        return instance;
    }

    private class ClientTask implements Runnable {
        private final Socket clientSocket;

        private ClientTask(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                System.out.println("TCP client connected!");
                BufferedReader inFromClient =
                        new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
                String requestMessage = inFromClient.readLine();
                String response = processMessage(requestMessage);
                outToClient.writeBytes(response);

                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
