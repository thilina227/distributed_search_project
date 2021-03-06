package distributed.computing;

import distributed.computing.config.NodeContext;
import distributed.computing.connector.HeartBeatDetector;
import distributed.computing.listner.Listener;
import distributed.computing.listner.UdpListener;
import distributed.computing.util.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.setProperty("log4j.configurationFile", "file:");

        PeerForm form = new PeerForm();
        form.setVisible(true);
        LOGGER.debug("Initializing...");


        new HeartBeatDetector().start();
        DBConnection.createDatabase();
    }

    private static void startListeners() {

        Listener udpListener = UdpListener.getInstance();
        udpListener.initListener(NodeContext.getPort());
    }
}
