package distributed.computing.bootstrap;

import distributed.computing.config.NodeContext;
import distributed.computing.domain.model.Operation;
import distributed.computing.util.MessageUtils;

/**
 * Created by dev on 11/19/16.
 *
 * //TODO use MessageUtils class instead of this
 */
public class BootstrapMessageUtils {

    private static final String REG_UNREG_MESSAGE_FORMAT = "%s %s %d %s" + System.lineSeparator();

    /**
     * Get registration message for bootstrap server
     *
     * @return String reg message eg: 0036 REG 129.82.123.45 5001 1234abcd
     */
    public static String constructRegMessage()throws IllegalArgumentException {
        String message = String.format(REG_UNREG_MESSAGE_FORMAT, Operation.REG, NodeContext.getIp(),
                NodeContext.getPort(), NodeContext.getUserName());
        return MessageUtils.prependLength(message);
    }


    /**
     * Get un-registration message for bootstrap server
     *
     * @return String reg message eg: 0038 UNREG 129.82.123.45 5001 1234abcd
     */
    public static String constructUnRegMessage() throws IllegalArgumentException {
        String message = String.format(REG_UNREG_MESSAGE_FORMAT, Operation.UNREG,
                NodeContext.getIp(), NodeContext.getPort(), NodeContext.getUserName());
        return MessageUtils.prependLength(message);
    }







}
