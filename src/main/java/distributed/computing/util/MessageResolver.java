package distributed.computing.util;

import distributed.computing.PeerForm;
import distributed.computing.config.NodeContext;
import distributed.computing.domain.model.Operation;
import distributed.computing.domain.model.PeerNode;
import distributed.computing.messaging.PeerMessageUtils;
import distributed.computing.messaging.broadcast.BroadCastMessenger;
import distributed.computing.messaging.broadcast.CommentBroadCastUtil;
import distributed.computing.messaging.broadcast.MessageCache;
import distributed.computing.messaging.broadcast.message.MessageRequest;
import distributed.computing.messaging.broadcast.message.SearchRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;

/**
 * Created by dev on 11/26/16.
 */
public class MessageResolver {

    private static final Logger LOGGER = LogManager.getLogger(MessageResolver.class.getName());

    private static final String PEER_MESSAGE_DELIMITER = " ";
    private static CommentBroadCastUtil commentBroadCastUtil = new CommentBroadCastUtil();

    public static String resolvePeerMessage(String message) {

        if (message != null && !message.equals("")) {

            if (message.contains(String.valueOf(Operation.PING))) {
                if (NodeContext.isOnline()) {
                    return PeerMessageUtils.constructHeartBeatResponse();
                } else {
                    return PeerMessageUtils.constructHeartBeatOfflineResponse();
                }

            }

            if (message.contains(String.valueOf(Operation.DISCONNECT))) {
                //message received in format: 0037 DISCONNECT localhost 9001 test2
                String chunks [] = message.split(PEER_MESSAGE_DELIMITER);
                PeerNode node = new PeerNode(chunks[2], Integer.parseInt(chunks[3]), chunks[4]);
                for (PeerNode parent : NodeContext.getParents()) {
                    if (node.getUsername().equals(parent.getUsername())) {
                        NodeContext.removeParent(parent);
                        LOGGER.info("Disconnected parent: {}", parent);
                    }
                }
                return PeerMessageUtils.constructDisconnectResponse();
            }

            if (message.contains(String.valueOf(Operation.CONNECT))) {
                //message received in format: 0034 CONNECT localhost 9001 test2
                String chunks [] = message.split(PEER_MESSAGE_DELIMITER);
                PeerNode parent = new PeerNode(chunks[2], Integer.parseInt(chunks[3]), chunks[4]);
                parent.setRelationship(PeerNode.Type.PARENT);
                NodeContext.addParent(parent);
                LOGGER.info("Connected with parent: {}", parent);
                return PeerMessageUtils.constructConnectResponse();
            }

            if (message.contains(String.valueOf(Operation.SER))) {
                LOGGER.debug("search request received");
                SearchRequest searchRequest = new SearchRequest(message);
                //do search if only TTL is not expired
                if (searchRequest.getTtl() >= searchRequest.getHops()) {
                    // do search if not in cache
                    if (!MessageCache.isInCache(searchRequest.getId())) {
                        SearchUtil.searchReturnAndBroadcast(searchRequest);
                    }
                } else {
                    LOGGER.info("Discarding search request! TTL > # of hops");
                }
                return PeerMessageUtils.constructSearchAckResponse();
            }


            if (message.contains(String.valueOf(Operation.RESULT))) {
                LOGGER.debug("search result received");
                //length RESULT uuid ip fileName
                String chunks [] = message.split(PEER_MESSAGE_DELIMITER);
                String id = chunks[2];
                String ip = chunks[3];
                String fileName = message.substring(message.indexOf(ip) + ip.length() + 1);
                PeerForm.addSearchResult(fileName, ip);
                return PeerMessageUtils.constructSearchResultAckResponse();
            }
            //TODO implement other operations

            if (message.split(" ")[1].equalsIgnoreCase("CMNT")) {
                LOGGER.debug("comment received");
                MessageRequest request = new MessageRequest(message);
                commentBroadCastUtil.handleReceivedComment(request);
                return MessageUtils.prependLength("CMNTACK 0");
            }

            if (message.split(" ")[1].equalsIgnoreCase("FILRTNG")) {
                LOGGER.debug("file rating received");
                MessageRequest request = new MessageRequest(message);
                commentBroadCastUtil.handleReceivedFileRating(request);
                return MessageUtils.prependLength("FILRTNGACK 0");
            }
        }

        return PeerMessageUtils.constructErrorResponse();
    }
}
