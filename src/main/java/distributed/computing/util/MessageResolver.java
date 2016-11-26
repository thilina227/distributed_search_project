package distributed.computing.util;

import distributed.computing.domain.model.Operation;
import distributed.computing.messaging.PeerMessageUtils;

/**
 * Created by dev on 11/26/16.
 */
public class MessageResolver {

    public static String resolvePeerMessage(String message) {

        if (message != null && !message.equals("")) {

            if (message.contains(String.valueOf(Operation.PING))) {
                return PeerMessageUtils.constructHeartBeatResponse();
            }

            if (message.contains(String.valueOf(Operation.CONNECT))) {
                return PeerMessageUtils.constructConnectResponse();
            }

            //TODO implement other operations
        }

        return PeerMessageUtils.constructErrorResponse();
    }
}
