package distributed.computing.listner;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dev on 11/12/16.
 *
 * This class is acting as a TCP server to receive messages from Bootstrap server
 */
public class TcpListener extends Listener {

    private static Listener instance;

    //Singleton
    private TcpListener() {
    }

    @Override
    public void initListener(int port) {
        System.out.println("Starting TCP Listener...");
        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    while (true) {
                        Socket connectionSocket = serverSocket.accept();
                        BufferedReader inFromClient =
                                new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                        String requestMessage = inFromClient.readLine();

                        //TODO process in a thread pool
                        String response = processMessage(requestMessage);
                        outToClient.writeBytes(response);

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
}
