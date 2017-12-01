package distributed.computing.connector;

import java.io.IOException;
import java.net.*;

/**
 * Created by dev on 11/10/16.
 *
 * This class is to send UDP messages to neighbors
 */
public class UdpCommunicator implements Communicator{

    // timeout in 5 seconds
    private static final int TIMEOUT_INTERVAL = 5000;

    @Override
    public String sendMessage(String host, int port, String message) throws IOException {
        message = message.trim();
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        DatagramSocket clientSocket = new DatagramSocket();
        clientSocket.setSoTimeout(TIMEOUT_INTERVAL);//timeout 5seconds
        InetAddress IPAddress = InetAddress.getByName(host);
        sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String responseText = new String(receivePacket.getData());
        clientSocket.close();
        return responseText;
    }
}
