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

    //TODO make singleton
    //TODO run in a new thread

    @Override
    public void initListener(int port) {
        System.out.println("Starting TCP Listener....");
        //TODO use a thread pool to accept concurrent messages

        String requestMessage;
        ServerSocket welcomeSocket = null;
        try {
            welcomeSocket = new ServerSocket(port);
            while (true) {
                Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient =
                        new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                requestMessage = inFromClient.readLine();

                String response = processMessage(requestMessage);
                outToClient.writeBytes(response);

            }

            //TODO graceful shutdown
        } catch (IOException e) {
            //TODO log
            e.printStackTrace();
        }

    }

    @Override
    public String processMessage(String message) {
        //TODO implement
        return "test";
    }
}
