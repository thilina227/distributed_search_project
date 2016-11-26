package distributed.computing.util;

import distributed.computing.config.NodeContext;
import distributed.computing.domain.model.Operation;
import distributed.computing.domain.model.PeerNode;
import distributed.computing.messaging.PeerMessageUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by dev on 11/26/16.
 */
public class MessageResolver {

    private static final Logger LOGGER = LogManager.getLogger(MessageResolver.class.getName());

    private static final String PEER_MESSAGE_DELIMITER = " ";

    public static String resolvePeerMessage(String message) {

        if (message != null && !message.equals("")) {

            if (message.contains(String.valueOf(Operation.PING))) {
                return PeerMessageUtils.constructHeartBeatResponse();
            }

            if (message.contains(String.valueOf(Operation.DISCONNECT))) {
                //message received in format: 0037 DISCONNECT localhost 9001 test2
                String chunks [] = message.split(PEER_MESSAGE_DELIMITER);
                PeerNode parent = new PeerNode(chunks[2], Integer.parseInt(chunks[3]), chunks[4]);
                NodeContext.removeParent(parent);
                LOGGER.info("Disconnected parent: {}", parent);
                return PeerMessageUtils.constructDisconnectResponse();
            }

            if (message.contains(String.valueOf(Operation.CONNECT))) {
                //message received in format: 0034 CONNECT localhost 9001 test2
                String chunks [] = message.split(PEER_MESSAGE_DELIMITER);
                PeerNode parent = new PeerNode(chunks[2], Integer.parseInt(chunks[3]), chunks[4]);
                NodeContext.addParent(parent);
                LOGGER.info("Connected with parent: {}", parent);
                return PeerMessageUtils.constructConnectResponse();
            }

            //TODO implement other operations
        }

        return PeerMessageUtils.constructErrorResponse();
    }
}
