package distributed.computing.messaging;

import distributed.computing.config.NodeContext;
import distributed.computing.domain.model.Operation;
import distributed.computing.domain.model.PeerNode;
import distributed.computing.util.MessageUtils;

/**
 * Created by dev on 11/26/16.
 */
public class PeerMessageUtils {

    private static final String CONNECT_MESSAGE_FORMAT = "%s %s %d %s";
    private static final String CONNECT_RESPONSE_KEYWORD = "CONNECTOK";
    private static final String DISCONNECT_RESPONSE_KEYWORD = "DISCONNECTOK";
    private static final String HEART_BEAT_RESPONSE_KEYWORD = "PINGOK";
    private static final String ERROR_RESPONSE_KEYWORD = "ERROR";
    private static final String SEARCH_ACK_RESPONSE_KEYWORD = "SERACK";
    private static final String RESULT_KEYWORD = "RESULT";
    private static final String SEARCH_RESULT_ACK_RESPONSE_KEYWORD = "RESULTACK";

    /**
     * Construct connectWithPeer message
     * eg: length CONNECT IP port username
     *
     * @param peerNode peer node to connectWithPeer
     * @return String message
     */
    public static String constructConnectMessage(PeerNode peerNode) {
        String message = String.format(CONNECT_MESSAGE_FORMAT, Operation.CONNECT, peerNode.getIp(), peerNode.getPort(), NodeContext.getUserName());
        message = MessageUtils.prependLength(message);
        return message;
    }

    /**
     * Construct connectWithPeer message
     * eg: length CONNECT IP port username
     *
     * @param peerNode peer node to connectWithPeer
     * @return String message
     */
    public static String constructDisconnectMessage(PeerNode peerNode) {
        String message = String.format(CONNECT_MESSAGE_FORMAT, Operation.DISCONNECT, peerNode.getIp(), peerNode.getPort(), NodeContext.getUserName());
        message = MessageUtils.prependLength(message);
        return message;
    }



    /**
     * Construct connect response message
     *
     * @return message
     */
    public static String constructConnectResponse() {
        String message = CONNECT_RESPONSE_KEYWORD + " " + 0;
        return MessageUtils.prependLength(message);
    }

    /**
     * Construct HEART BEAT response message
     *
     * @return message
     */
    public static String constructHeartBeatResponse() {
        String message = HEART_BEAT_RESPONSE_KEYWORD + " " + 0;
        return MessageUtils.prependLength(message);
    }

    /**
     * Construct ERROR response message
     *
     * @return message
     */
    public static String constructErrorResponse() {
        String message = ERROR_RESPONSE_KEYWORD + " " + 9999;
        return MessageUtils.prependLength(message);
    }

    /**
     * Construct disconnect response
     * */
    public static String constructDisconnectResponse() {
        String message = DISCONNECT_RESPONSE_KEYWORD + " " + 0;
        return MessageUtils.prependLength(message);
    }

    /**
     * Construct search acknowledgement response
     * */
    public static String constructSearchAckResponse() {
        String message = SEARCH_ACK_RESPONSE_KEYWORD + " " + 0;
        return MessageUtils.prependLength(message);
    }


    /**
     * Construct message for search result
     * */
    public static String constructSearchResultRequest(String fileName, String id) {
        String message = RESULT_KEYWORD + " " + id + " " + NodeContext.getIp() + " " +fileName;
        return MessageUtils.prependLength(message);
    }

    /**
     * Construct search result acknowledgement
     * */
    public static String constructSearchResultAckResponse() {
        String message = SEARCH_RESULT_ACK_RESPONSE_KEYWORD + " " + 0;
        return MessageUtils.prependLength(message);
    }
}
