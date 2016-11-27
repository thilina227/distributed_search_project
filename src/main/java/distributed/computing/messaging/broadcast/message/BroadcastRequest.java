package distributed.computing.messaging.broadcast.message;

import distributed.computing.util.MessageUtils;

import java.util.UUID;

/**
 * Created by dev on 11/26/16.
 */
public class BroadcastRequest {

    private static final String SEARCH_MESSAGE_FORMAT = "%s %s %d %s %d %d %s%s";
    private static final String DELIMITER = " ";

    private String id;
    private String message;
    private String operation;
    private String sourceIp;
    private int sourcePort;
    private int hops;
    private int ttl;

    public BroadcastRequest(String id, String message, String operation, String sourceIp, int sourcePort, int hops, int ttl) {
        this.id = id;
        this.message = message;
        this.operation = operation;
        this.sourceIp = sourceIp;
        this.sourcePort = sourcePort;
        this.hops = hops;
        this.ttl = ttl;
    }

    public BroadcastRequest(String message, String operation, String sourceIp, int sourcePort, int hops, int ttl) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.operation = operation;
        this.sourceIp = sourceIp;
        this.sourcePort = sourcePort;
        this.hops = hops;
        this.ttl = ttl;
    }

    public BroadcastRequest(String message) {
        //length SER sourceip sourceport message hops ttl messageId
        String chunks[] = message.split(DELIMITER);
        this.operation = chunks[1];
        this.sourceIp = chunks[2];
        this.sourcePort = Integer.parseInt(chunks[3]);
        this.message = chunks[4];
        this.hops = Integer.parseInt(chunks[5]);
        this.ttl = Integer.parseInt(chunks[6]);
        this.id = chunks[7];
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        //length SER sourceip sourceport message hops ttl messageId
        String message = String.format(SEARCH_MESSAGE_FORMAT, this.operation, this.sourceIp,
                this.sourcePort, this.message, this.hops, this.ttl, this.id, System.lineSeparator());
        message = MessageUtils.prependLength(message);
        return message;
    }
}
