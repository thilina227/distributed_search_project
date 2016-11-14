package distributed.computing.listner;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dev on 11/10/16.
 *
 * This class is acting as a UDP server to receive messages from neighbors
 */
public class UdpListener extends Listener {

    private static final int THREAD_POOL_SIZE = 10;

    private static Listener instance;

    private int port;
    private byte[] receiveData = new byte[1024];
    private byte[] sendData = new byte[1024];

    //Singleton
    private UdpListener() {
    }

    @Override
    public void initListener(int port) {
        System.out.println("Starting UDP Listener...");

        final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                    DatagramSocket datagramSocket = new DatagramSocket(port);

                    while (true) {
                        DatagramPacket datagramPacket = new DatagramPacket(receiveData, receiveData.length);
                        datagramSocket.receive(datagramPacket);
                        clientProcessingPool.submit(new ClientTask(datagramSocket, datagramPacket));
                    }
                    //TODO graceful shutdown
                } catch (SocketException e) {
                    //TODO log
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
        System.out.println("UDP Listener started on port : " + port);
    }

    @Override
    public String processMessage(String message) {
        //TODO implement
        return "test";
    }

    /**
     * Get current instance
     *
     * @return UdpListener instance
     * */
    public static Listener getInstance() {
        if (instance == null) {
            instance = new UdpListener();
        }
        return instance;
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    //client task executor
    private class ClientTask implements Runnable {
        private final DatagramSocket clientSocket;
        private final DatagramPacket datagramPacket;

        private ClientTask(DatagramSocket clientSocket, DatagramPacket datagramPacket) {
            this.clientSocket = clientSocket;
            this.datagramPacket = datagramPacket;
        }

        @Override
        public void run() {
            try {
                System.out.println("UDP client connected!");
                String receivedText = new String(datagramPacket.getData());
                System.out.println("received: " + receivedText);

                InetAddress senderAddress = datagramPacket.getAddress();
                int senderPort = datagramPacket.getPort();

                String response = processMessage(receivedText);
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, senderAddress, senderPort);
                sendPacket.setData(response.getBytes());
                clientSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
