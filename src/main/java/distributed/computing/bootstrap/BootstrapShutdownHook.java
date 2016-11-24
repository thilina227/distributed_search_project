package distributed.computing.bootstrap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    }
}
