package distributed.computing;

import distributed.computing.listner.Listener;
import distributed.computing.listner.TcpListener;
import distributed.computing.listner.UdpListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    //TODO get from config
    private static String bootstrapServerIp = "localhost";
    private static int bootstrapServerPort = 9000;

    private static int udpPort = 9001;//TODO get from args

    public static void main(String[] args) {
        LOGGER.info("******************Starting client app!*******************");
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
