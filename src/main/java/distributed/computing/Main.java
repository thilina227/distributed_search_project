package distributed.computing;

import distributed.computing.listner.Listener;
import distributed.computing.listner.TcpListener;
import distributed.computing.listner.UdpListener;

public class Main {

    //TODO get from config
    private static String bootstrapServerIp = "localhost";
    private static int bootstrapServerPort = 9000;

    private static int udpPort = 9001;//get from args

    public static void main(String[] args) {
        //TODO REG with BS

        startListeners();
    }

    private static void startListeners() {
        Listener tcpListener = TcpListener.getInstance();
        tcpListener.initListener(bootstrapServerPort);

        Listener udpListener = UdpListener.getInstance();
        udpListener.initListener(udpPort);
    }
}
