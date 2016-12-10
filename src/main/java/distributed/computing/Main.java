package distributed.computing;

import distributed.computing.config.NodeContext;
import distributed.computing.connector.HeartBeatDetector;
import distributed.computing.listner.Listener;
import distributed.computing.listner.UdpListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        PeerForm form = new PeerForm();
        form.setVisible(true);
        LOGGER.debug("Initializing...");


        new HeartBeatDetector().start();
    }

    private static void startListeners() {

        Listener udpListener = UdpListener.getInstance();
        udpListener.initListener(NodeContext.getPort());
    }
}
