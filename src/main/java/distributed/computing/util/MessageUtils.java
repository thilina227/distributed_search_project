package distributed.computing.util;

import distributed.computing.domain.model.Operation;
/**
 * Created by dev on 11/11/16.
 */
public class MessageUtils {

    //TODO implement
    public static String createMsg(Operation type, String ipAddress, int port, String name){
        String msg = "";
        int length;
        String strlen;
        String paddedlen;
        
        if(type == Operation.REG){
            msg = String.format("REG %s %d %s", ipAddress, port, name); 
        }
        else if(type == Operation.JOIN){
             msg = String.format("JOIN %s %d", ipAddress, port); 
        }
        else if(type == Operation.SER){
            msg = String.format("SER %s %d %s", ipAddress, port, name); 
        }
        
        length = msg.length() + 5;
        strlen = Integer.toString(length);
        paddedlen = "0000".substring(strlen.length()) + strlen;
        
        msg = paddedlen + " " + msg;
        return msg;
    }
}
