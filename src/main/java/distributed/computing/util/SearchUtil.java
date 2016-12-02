package distributed.computing.util;

/**
 * Created by thilina on 12/2/16.
 */
public class SearchUtil {
    //break the search term into words
    public static final String SEARCH_DELIMITER = " ";

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


    }

    public static final void doLocalSearch() {


    }

}
