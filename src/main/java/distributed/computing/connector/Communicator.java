package distributed.computing.connector;

/**
 * Created by dev on 11/10/16.
 */
public interface Communicator {

    /**
     * @param host ip address/ host name
     * @param port port
     * @param message message
     * @return response message
     * */
    String sendMessage(String host, int port, String message);
}
