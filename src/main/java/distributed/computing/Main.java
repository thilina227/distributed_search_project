package distributed.computing;

import distributed.computing.config.NodeContext;
import distributed.computing.listner.Listener;
import distributed.computing.listner.UdpListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        //TODO replace this with form
//        if (args.length < 5) {
//            System.out.println("Usage: clientapp <client ip> <username> <client port> <bootstrap ip> <bootstrap port>");
//        } else {


//            NodeContext.setIp(Utils.getIP());
//            NodeContext.setPort(Integer.parseInt(args[1]));
//            NodeContext.setUserName(args[2]);

//            BootstrapServerConfig.setHost(args[3]);
//            BootstrapServerConfig.setPort(Integer.parseInt(args[4]));
//            LOGGER.info("******************Starting client app!*******************");
//            startListeners();
//            Bootstrap.register();
//            Runtime.getRuntime().addShutdownHook(new BootstrapShutdownHook());
//            MessageCache.initCachingScheduler();//init caching scheduler
//        }
        peerForm form = new peerForm();
        form.setVisible(true);
        LOGGER.debug("Initializing...");

        //TODO get server details from form
//        peerForm form = new peerForm();
//        form.setVisible(true);
        //startListeners();
        //Bootstrap.register();//registering with bootstrap server
    }

    private static void startListeners() {
        //TODO remove TcpListener
//        Listener tcpListener = TcpListener.getInstance();
//        tcpListener.initListener(bootstrapServerPort);

        Listener udpListener = UdpListener.getInstance();
        udpListener.initListener(NodeContext.getPort());
    }
}
