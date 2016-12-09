package distributed.computing.util;

import distributed.computing.config.NodeContext;
import distributed.computing.domain.model.FileManager;
import distributed.computing.domain.model.Operation;
import distributed.computing.messaging.broadcast.BroadCastMessenger;
import distributed.computing.messaging.broadcast.message.BroadcastRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.soap.Node;
import java.util.List;

/**
 * Created by thilina on 12/2/16.
 */
public class SearchUtil {

    private static final Logger LOGGER = LogManager.getLogger(SearchUtil.class.getName());


    //break the search term into words
    private static final String SEARCH_DELIMITER = " ";
    private static final int TTL = 2;

    public static final void search(String searchTerm) {
        if (searchTerm.contains(SEARCH_DELIMITER)) {
            String[] kewords = searchTerm.split(SEARCH_DELIMITER);
            for (String keyword : kewords) {
                doSearch(keyword);

            }
        } else {
            doSearch(searchTerm);
        }

    }

    private static void doSearch(String searchTerm) {
        LOGGER.debug("searching for {}", searchTerm);

        //do local search
        doLocalSearch(searchTerm);

        //create a broadcast search request
        BroadCastMessenger broadCastMessenger = new BroadCastMessenger();
        BroadcastRequest broadcastRequest = new BroadcastRequest(searchTerm, String.valueOf(Operation.SER), NodeContext.getIp(),
                NodeContext.getPort(), 1, TTL, NodeContext.getUserName());
        broadCastMessenger.broadcast(broadcastRequest);

    }

    public static final void doLocalSearch(String keyword) {
        FileManager fileManager = new FileManager();
        List<String> files =  fileManager.searchFile(keyword);
        //TODO view local search results
    }

}
