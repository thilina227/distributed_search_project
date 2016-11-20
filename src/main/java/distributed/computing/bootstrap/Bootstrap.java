package distributed.computing.bootstrap;

import distributed.computing.bootstrap.response.RegResponse;
import distributed.computing.config.NodeContext;
import distributed.computing.connector.TcpCommunicator;
import distributed.computing.domain.model.PeerNode;
import distributed.computing.util.ErrorCodeResolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Random;

/**
 * Created by dev on 11/17/16.
 */
public class Bootstrap {

    private static final Logger LOGGER = LogManager.getLogger(Bootstrap.class.getName());

    private static String UNREG_OK_KEYWORD = "UNROK";
    private static String REG_OK_KEYWORD = "REGOK";

    private static PeerNode child1 = null;
    private static PeerNode child2 = null;

    /**
     * Register the node with bootstrap server
     *
     * @return boolean success/fail
     */
    public static boolean register() {
        TcpCommunicator tcpCommunicator = new TcpCommunicator();
        try {
            String response = tcpCommunicator.sendMessage(BootstrapServer.getBootstrapServerIp(),
                    BootstrapServer.getPort(), BootstrapMessageUtils.constructRegMessage());

            RegResponse regResponse = new RegResponse(response);
            if (regResponse.getStatusCode() == ErrorCodeResolver.OK_SUCCESS) {

                connectPeers(regResponse);


                return true;
            } else {
                //TODO handle
            }

        } catch (IllegalArgumentException e) {
            LOGGER.error("IllegalArgumentException occurred when connecting Bootstrap Server", e);
        } catch (IOException e) {
            LOGGER.error("IOException occurred when connecting Bootstrap Server", e);
        }
        return false;
    }

    private static void connectPeers(RegResponse regResponse) {
        int nodeCount = regResponse.getPeerNodes().size();
        if (nodeCount == 0) {
            //no peer nodes yet in the BS
            LOGGER.info("No peer nodes in the network yet");
        } else if (nodeCount == 1) {
            child1 = regResponse.getPeerNodes().get(0);
        } else if (nodeCount == 2) {
            child1 = regResponse.getPeerNodes().get(0);
            child2 = regResponse.getPeerNodes().get(1);
        } else {
            child1 = regResponse.getPeerNodes().get(new Random().nextInt(nodeCount));
            child2 = regResponse.getPeerNodes().get(new Random().nextInt(nodeCount));
        }

        //TODO connect message to peers

        if (child1 != null)
            NodeContext.addChild(child1);
        if (child2 != null)
            NodeContext.addChild(child2);
    }

    public static boolean unregister() {
        TcpCommunicator tcpCommunicator = new TcpCommunicator();
        try {
            String response = tcpCommunicator.sendMessage(BootstrapServer.getBootstrapServerIp(),
                    BootstrapServer.getPort(), BootstrapMessageUtils.constructUnRegMessage());

            //TODO validate response
            //if (response is ok return true)

        } catch (IllegalArgumentException e) {
            LOGGER.error("IllegalArgumentException occurred when connecting Bootstrap Server", e);
        } catch (IOException e) {
            LOGGER.error("IOException occurred when connecting Bootstrap Server", e);
        }
        return false;
    }

}
