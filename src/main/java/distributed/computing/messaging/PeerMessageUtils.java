package distributed.computing.messaging;

import distributed.computing.config.NodeContext;
import distributed.computing.domain.model.Operation;
import distributed.computing.domain.model.PeerNode;
import distributed.computing.util.MessageUtils;

/**
 * Created by dev on 11/26/16.
 */
public class PeerMessageUtils {

    private static final String CONNECT_MESSAGE_FORMAT = "%s %s %d %s%s";

    /**
     * Construct connect message
     * eg: length CONNECT IP port username
     * @param peerNode peer node to connect
     * @return String message
     * */
    public static String constructConnectMessage(PeerNode peerNode) {
        String message = String.format(CONNECT_MESSAGE_FORMAT, Operation.CONNECT, peerNode.getIp(), peerNode.getPort(), NodeContext.getUserName(), System.lineSeparator());
        message = MessageUtils.prependLength(message);
        return message;
    }
}
