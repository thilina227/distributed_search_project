package distributed.computing.bootstrap.response;

import distributed.computing.domain.model.PeerNode;
import distributed.computing.util.ErrorCodeResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by dev on 11/20/16.
 */
public class RegResponse extends BootstrapResponse {

    private static final String empty = new String();
    private List<PeerNode> peerNodes = new ArrayList<>();

    public RegResponse(String messageString) {
        super(messageString);
        if (this.getMessageBody() != null && !empty.equals(this.getStatusMessage())) {
            if (this.getMessageBody().contains(MESSAGE_DELIMITER)) {
                StringTokenizer tokenizer = new StringTokenizer(MESSAGE_DELIMITER);
                while (tokenizer.hasMoreTokens()) {
                    PeerNode peerNode = new PeerNode(tokenizer.nextToken(), tokenizer.nextToken());
                    peerNodes.add(peerNode);
                }
            }
        }
    }

    @Override
    public String getStatusMessage() {
        return ErrorCodeResolver.getRegStatusMessage(this.getStatusCode());
    }

    public List<PeerNode> getPeerNodes() {
        return peerNodes;
    }
}
