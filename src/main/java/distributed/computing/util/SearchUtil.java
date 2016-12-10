package distributed.computing.util;

import distributed.computing.PeerForm;
import distributed.computing.config.NodeContext;
import distributed.computing.connector.UdpCommunicator;
import distributed.computing.domain.model.FileManager;
import distributed.computing.domain.model.Operation;
import distributed.computing.messaging.PeerMessageUtils;
import distributed.computing.messaging.broadcast.BroadCastMessenger;
import distributed.computing.messaging.broadcast.message.SearchRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Created by thilina on 12/2/16.
 */
public class SearchUtil {

    private static final Logger LOGGER = LogManager.getLogger(SearchUtil.class.getName());


    //break the search term into words
    private static final String SEARCH_DELIMITER = " ";
    private static final int TTL = 5;

    public static final void search(String searchTerm) {
        if (searchTerm.contains(SEARCH_DELIMITER)) {
            String[] kewords = searchTerm.split(SEARCH_DELIMITER);
            for (String keyword : kewords) {
                initiateSearch(keyword);

            }
        } else {
            initiateSearch(searchTerm);
        }

    }

    private static void initiateSearch(String searchTerm) {
        LOGGER.debug("searching for {}", searchTerm);

        //do local search
        doLocalSearch(searchTerm);

        //create a broadcast search request
        BroadCastMessenger broadCastMessenger = new BroadCastMessenger();
        SearchRequest searchRequest = new SearchRequest(searchTerm, String.valueOf(Operation.SER), NodeContext.getIp(),
                NodeContext.getPort(), 1, TTL, NodeContext.getUserName());
        broadCastMessenger.broadcast(searchRequest);

    }

    public static void doLocalSearch(String keyword) {
        List<String> files =  FileManager.searchFile(keyword);

        if (!files.isEmpty()) {
            for (String file : files) {
                PeerForm.addSearchResult(file, "local");
            }
        }
    }



    /**
     * Search local files and send the results back to the source node
     * */
    public static void searchReturnAndBroadcast(SearchRequest searchRequest) {
        String keyword = searchRequest.getMessage();
        List<String> files = FileManager.searchFile(keyword);

        if (!files.isEmpty()) {
            for (String filename: files) {
                String message = PeerMessageUtils.constructSearchResultRequest(filename, searchRequest.getId());
                try {
                    new UdpCommunicator().sendMessage(searchRequest.getSourceIp(), searchRequest.getSourcePort(), message);
                } catch (IOException e) {
                    LOGGER.error("Failed sending search result", e);
                }
            }
        }

        //broadcast search request to neighbors
        BroadCastMessenger broadCastMessenger = new BroadCastMessenger();
        broadCastMessenger.broadcast(searchRequest);
    }

}
