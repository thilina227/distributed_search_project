package distributed.computing.bootstrap;

/**
 * Created by dev on 11/17/16.
 */
public class BootstrapServer {
    private static String bootstrapServerIp;
    private static int port;

    public BootstrapServer() {
    }

    public static String getBootstrapServerIp() {
        return bootstrapServerIp;
    }

    public static void setBootstrapServerIp(String bootstrapServerIp) {
        BootstrapServer.bootstrapServerIp = bootstrapServerIp;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        BootstrapServer.port = port;
    }
}
