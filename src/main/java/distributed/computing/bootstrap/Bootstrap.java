package distributed.computing.bootstrap;

import distributed.computing.bootstrap.response.RegResponse;
import distributed.computing.config.BootstrapServerConfig;
import distributed.computing.config.NodeContext;
import distributed.computing.connector.TcpCommunicator;
import distributed.computing.domain.model.PeerNode;
import distributed.computing.messaging.Connect;
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
        LOGGER.info("Registering with Bootstrap server");
        TcpCommunicator tcpCommunicator = new TcpCommunicator();
        try {
            String response = tcpCommunicator.sendMessage(BootstrapServerConfig.getHost(),
                    BootstrapServerConfig.getPort(), BootstrapMessageUtils.constructRegMessage());

            RegResponse regResponse = new RegResponse(response);
            if (regResponse.getStatusCode() < ErrorCodeResolver.OK_MIN_FAIL_RANGE) {
                //This means reg is success
                connectPeers(regResponse);


                return true;
            } else {
                LOGGER.error("Registration failed, responseCode: {}, responseMessage: {}",
                        regResponse.getStatusCode(), regResponse.getStatusMessage());
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
            LOGGER.info("1 node in the network");
            if (!Connect.connectWithPeer(child1)) {
                child1 = null;
            }
        } else if (nodeCount == 2) {
            child1 = regResponse.getPeerNodes().get(0);
            if(!Connect.connectWithPeer(child1))
                child1 = null;
            child2 = regResponse.getPeerNodes().get(1);
            if(!Connect.connectWithPeer(child2))
                child2 = null;
            LOGGER.info("2 nodes in the network");
        } else {
            LOGGER.info(nodeCount + " nodes in the network");


            child1 = connectRandomChild(regResponse, nodeCount);
            child2 = connectRandomChild(regResponse, nodeCount);
            if (child1 != null && child2 != null && child1.getUsername().equals(child2.getUsername())) {
                child2 = connectRandomChild(regResponse, nodeCount);
                if (child1.getUsername().equals(child2.getUsername())) {
                    child2 = null;
                }
            }

            child1 = regResponse.getPeerNodes().get(new Random().nextInt(nodeCount));
            child2 = regResponse.getPeerNodes().get(new Random().nextInt(nodeCount));

        }

        //TODO connectWithPeer message to peers

        if (child1 != null)
            NodeContext.addChild(child1);
        if (child2 != null)
            NodeContext.addChild(child2);

        LOGGER.info("picked children: {}", NodeContext.getChildren());
    }

    /**
     * Connect with a random peer node as a parent
     *
     * @param regResponse reg response
     * @param nodeCount   number of nodes in the network
     */
    private static PeerNode connectRandomChild(RegResponse regResponse, int nodeCount) {
        PeerNode child = regResponse.getPeerNodes().get(new Random().nextInt(nodeCount));
        if (Connect.connectWithPeer(child)) {
            return child;
        } else {
            child = regResponse.getPeerNodes().get(new Random().nextInt(nodeCount));
            if (Connect.connectWithPeer(child)) {
                return child;
            } else {
                return null;
            }
        }
    }

    public static boolean unregister() {
        LOGGER.info("UnRegistering from Bootstrap server");

        TcpCommunicator tcpCommunicator = new TcpCommunicator();
        try {
            String response = tcpCommunicator.sendMessage(BootstrapServerConfig.getHost(),
                    BootstrapServerConfig.getPort(), BootstrapMessageUtils.constructUnRegMessage());

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
