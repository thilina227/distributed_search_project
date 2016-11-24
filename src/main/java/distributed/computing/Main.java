package distributed.computing;

import distributed.computing.bootstrap.Bootstrap;
import distributed.computing.domain.model.*;
import distributed.computing.listner.Listener;
import distributed.computing.listner.TcpListener;
import distributed.computing.listner.UdpListener;
import distributed.computing.util.*;
import java.util.List;
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
        peerForm form = new peerForm();
        form.setVisible(true);
        //startListeners();
        //Bootstrap.register();//registering with bootstrap server
    }

    private static void startListeners() {
        //TODO remove TcpListener
//        Listener tcpListener = TcpListener.getInstance();
//        tcpListener.initListener(bootstrapServerPort);

        Listener udpListener = UdpListener.getInstance();
        udpListener.initListener(udpPort);
    }
}
