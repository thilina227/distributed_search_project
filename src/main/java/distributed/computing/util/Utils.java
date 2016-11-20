/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributed.computing.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author yasitham
 */
public class Utils {
    
    public static String getIP(){
        
        String ip = "";
        try {
             InetAddress IP=InetAddress.getLocalHost();
             ip = IP.getHostAddress();  
          } catch (UnknownHostException e) {

          }
        return ip;
    }
}
