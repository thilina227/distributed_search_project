package distributed.computing.config;

import distributed.computing.domain.model.PeerNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev on 11/17/16.
 */
public class NodeContext {
    private static String ip;
    private static int port;
    private static String userName;
    private static List<PeerNode> peerNodes = new ArrayList<>();

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        NodeContext.ip = ip;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        NodeContext.port = port;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        NodeContext.userName = userName;
    }


    /**
     * Add a peerNode as a child
     */
    public static void addChild(PeerNode peerNode) {
        peerNode.setRelationship(PeerNode.Type.CHILD);
        peerNodes.add(peerNode);
    }

    /**
     * Get child nodes
     *
     * @return children
     */
    public static List<PeerNode> getChildren() {
        List<PeerNode> children = new ArrayList<>();
        for (PeerNode peerNode : peerNodes) {
            if (peerNode.getRelationship().equals(PeerNode.Type.CHILD)) {
                children.add(peerNode);
            }
        }
        return children;
    }

    /**
     * Add a peerNode as a parent
     */
    public static void addParent(PeerNode peerNode) {
        peerNode.setRelationship(PeerNode.Type.PARENT);
        peerNodes.add(peerNode);
    }


    /**
     * Get parent nodes
     *
     * @return parents
     */
    public static List<PeerNode> getParents() {
        List<PeerNode> parents = new ArrayList<>();
        for (PeerNode peerNode : peerNodes) {
            if (peerNode.getRelationship().equals(PeerNode.Type.PARENT)) {
                parents.add(peerNode);
            }
        }
        return parents;
    }
}
