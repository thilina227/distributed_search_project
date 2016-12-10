package distributed.computing.messaging.broadcast;

import distributed.computing.config.NodeContext;
import distributed.computing.connector.UdpCommunicator;
import distributed.computing.domain.model.PeerNode;
import distributed.computing.messaging.broadcast.message.SearchRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Created by dev on 11/26/16.
 */
public class BroadCastMessenger {


    private static final Logger LOGGER = LogManager.getLogger(BroadCastMessenger.class.getName());

    private static final String BROADCAST_RESPONSE_ACK = "ACK";

    /**
     * Broadcast message to network
     *
     * @param searchRequest broadcast message
     * @return response
     */
    public void broadcast(SearchRequest searchRequest) {
        String predecessorNode = searchRequest.getPredecessor().trim();
        if (MessageCache.isInCache(searchRequest.getId())) {
            //Do nothing
            LOGGER.debug("Discarding message {}", searchRequest.getId());
        } else {
            MessageCache.addCache(searchRequest.getId());
            for (PeerNode parent : NodeContext.getParents()) {
                //avoid sending back to predecessor
                String parentNode = parent.getUsername().trim();
                if (!parentNode.equals(predecessorNode)) {
                    try {
                        searchRequest.setPredecessor(NodeContext.getUserName());
                        searchRequest.setHops(searchRequest.getHops() + 1);
                        new UdpCommunicator().sendMessage(parent.getIp(), parent.getPort(), searchRequest.toString());
                    } catch (IOException e) {
                        LOGGER.error("Could not send message to parent {}", parent);
                    }
                }
            }
            for (PeerNode child : NodeContext.getChildren()) {
                String childNode = child.getUsername().trim();
                //avoid sending back to predecessor
                if (!childNode.equalsIgnoreCase(predecessorNode)) {
                    try {
                        searchRequest.setHops(searchRequest.getHops() + 1);
                        searchRequest.setPredecessor(NodeContext.getUserName());
                        new UdpCommunicator().sendMessage(child.getIp(), child.getPort(), searchRequest.toString());
                    } catch (IOException e) {
                        LOGGER.error("Could not send message to child {}", child);
                    }
                }
            }

        }
    }

}
