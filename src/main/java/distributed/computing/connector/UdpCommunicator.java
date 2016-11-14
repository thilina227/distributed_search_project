package distributed.computing.connector;

import java.io.IOException;
import java.net.*;

/**
 * Created by dev on 11/10/16.
 *
 * This class is to send UDP messages to neighbors
 */
public class UdpCommunicator implements Communicator{

    private byte[] sendData = new byte[1024];
    private byte[] receiveData = new byte[1024];

    @Override
    public String sendMessage(String host, int port, String message) throws IOException {
        DatagramSocket clientSocket = new DatagramSocket();
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
