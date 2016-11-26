package distributed.computing.bootstrap;

import distributed.computing.config.NodeContext;
import distributed.computing.domain.model.PeerNode;
import distributed.computing.messaging.Connect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.CompositeName;
import java.util.List;

/**
 * Created by thilina on 11/25/16.
 *
 * Shutdown hook for client app
 */
public class BootstrapShutdownHook extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(BootstrapShutdownHook.class.getName());


    @Override
    public void run() {
        LOGGER.info("Shutting down...");
        Bootstrap.unregister();

        List<PeerNode> children = NodeContext.getChildren();
        for (PeerNode child : children) {
            Connect.disconnectFromPeer(child);
        }
    }
}
