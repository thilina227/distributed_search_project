package distributed.computing.bootstrap;

import distributed.computing.config.NodeContext;
import distributed.computing.domain.model.Operation;

/**
 * Created by dev on 11/19/16.
 *
 * //TODO use MessageUtils class instead of this
 */
public class BootstrapMessageUtils {

    private static String REG_UNREG_MESSAGE_FORMAT = "%s %s %d %s";

    private static final int MESSAGE_LENGTH_TOKEN_LENGH = 4;

    /**
     * Get registration message for bootstrap server
     *
     * @return String reg message eg: 0036 REG 129.82.123.45 5001 1234abcd
     */
    public static String constructRegMessage()throws IllegalArgumentException {
        String message = String.format(REG_UNREG_MESSAGE_FORMAT, Operation.REG, NodeContext.getIp(),
                NodeContext.getPort(), NodeContext.getUserName());
        return prependLength(message);
    }


    /**
     * Get un-registration message for bootstrap server
     *
     * @return String reg message eg: 0038 UNREG 129.82.123.45 5001 1234abcd
     */
    public static String constructUnRegMessage() throws IllegalArgumentException {
        String message = String.format(REG_UNREG_MESSAGE_FORMAT, Operation.UNREG,
                NodeContext.getIp(), NodeContext.getPort(), NodeContext.getUserName());
        return prependLength(message);
    }


    /**
     * Prepend message length to the message
     *
     * @param message message eg: REG 129.82.123.45 5001 1234abcd
     * @return formatted message eg: 0036 REG 129.82.123.45 5001 1234abcd
     */
    private static String prependLength(String message) throws IllegalArgumentException {
        message = ' ' + message;//prepend a space
        message = formatToFourDigitString(message.length() + MESSAGE_LENGTH_TOKEN_LENGH) + message;
        return message;
    }


    /**
     * Format an integer into a four digit string value
     *
     * @param number
     * @return formatted String
     */
    private static String formatToFourDigitString(int number) throws IllegalArgumentException {
        String strNumber = String.valueOf(number);
        if (strNumber.length() > MESSAGE_LENGTH_TOKEN_LENGH) {
            throw new IllegalArgumentException("Message is too long");
        } else {
            while (strNumber.length() != MESSAGE_LENGTH_TOKEN_LENGH) {
                strNumber = '0' + strNumber;
            }
        }
        return strNumber;
    }





}
