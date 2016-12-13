package distributed.computing.util;

import distributed.computing.domain.model.PeerNode;

import java.util.List;

/**
 * Created by dev on 12/12/16.
 */
public interface PeerNodeObserver {
    void onChildChanged(List<PeerNode> children);
}
