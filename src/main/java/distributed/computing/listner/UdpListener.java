package distributed.computing.listner;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by dev on 11/10/16.
 *
 * This class is acting as a UDP server to receive messages from neighbors
 */
public class UdpListener extends Listener {

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


        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                    DatagramSocket datagramSocket = new DatagramSocket(port);

                    while (true) {
                        DatagramPacket datagramPacket = new DatagramPacket(receiveData, receiveData.length);
                        datagramSocket.receive(datagramPacket);
                        String receivedText = new String(datagramPacket.getData());

                        InetAddress senderAddress = datagramPacket.getAddress();
                        int senderPort = datagramPacket.getPort();

                        //TODO use a thread pool to accept concurrent messages
                        String response = processMessage(receivedText);
                        DatagramPacket sendPacket =
                                new DatagramPacket(sendData, sendData.length, senderAddress, senderPort);
                        sendPacket.setData(response.getBytes());
                        datagramSocket.send(sendPacket);
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
        System.out.println("UDP Listener started!");
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
}
