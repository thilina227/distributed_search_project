package distributed.computing.domain.model;

/**
 * Created by dev on 11/10/16.
 */
public class PeerNode {

    public enum Type {
        PARENT, CHILD
    }

    private String ip;
    private String port;
    private String username;
    private Type relationship;

    public PeerNode(String ip, String port, String username, Type relationship) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.relationship = relationship;
    }

    public PeerNode(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Type getRelationship() {
        return relationship;
    }

    public void setRelationship(Type relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return "PeerNode{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", username='" + username + '\'' +
                ", relationship=" + relationship +
                '}';
    }
}
