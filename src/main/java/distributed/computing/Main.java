package distributed.computing;

import distributed.computing.domain.model.Operation;
import distributed.computing.listner.Listener;
import distributed.computing.listner.TcpListener;
import distributed.computing.listner.UdpListener;
import distributed.computing.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    //TODO get from config
    private static String bootstrapServerIp = "localhost";
    private static int bootstrapServerPort = 5000;

    private static int udpPort = 5001;//TODO get from args

    public static void main(String[] args) {
        LOGGER.info("******************Starting client app!*******************");
        //TODO REG with BS
      
        String s = MessageUtils.createMsg(Operation.REG, Utils.getIP(), udpPort, "1234abcd");
        System.out.println(s);
      //  startListeners();
    }

    private static void startListeners() {
        Listener tcpListener = TcpListener.getInstance();
        tcpListener.initListener(bootstrapServerPort);

        Listener udpListener = UdpListener.getInstance();
        udpListener.initListener(udpPort);
    }
}
