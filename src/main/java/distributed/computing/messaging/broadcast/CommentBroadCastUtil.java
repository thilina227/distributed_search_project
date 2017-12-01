package distributed.computing.messaging.broadcast;

import distributed.computing.PeerForm;
import distributed.computing.config.NodeContext;
import distributed.computing.domain.model.Comment;
import distributed.computing.messaging.broadcast.message.MessageRequest;
import distributed.computing.sevice.CommentService;
import distributed.computing.sevice.FileRatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.UUID;

public class CommentBroadCastUtil {
    private static final Logger LOGGER = LogManager.getLogger(CommentBroadCastUtil.class.getName());
    private CommentService commentService = new CommentService();
    private FileRatingService fileRatingService = new FileRatingService();

    public void broadcastComment(final Comment comment){
        new Thread(){
            @Override
            public void run() {
                LOGGER.debug("broadcasting comment");
                MessageRequest request = new MessageRequest(UUID.randomUUID().toString(),
                        comment.getFileHash() + " " + comment.getCommentId() + " " + comment.getNode() + " " +comment.getTime() + " " + comment.getComment(),
                        "CMNT", 0, (int)PeerForm.spTtl.getValue(), NodeContext.getUserName());
                BroadCastMessenger broadCastMessenger = new BroadCastMessenger();
                broadCastMessenger.broadcast(request);
            }
        }.start();
    }

    public void handleReceivedComment(final MessageRequest request) {
        new Thread() {
            @Override
            public void run() {
                Comment comment = new Comment();
                String chunks[] = request.getData().split(" ");
                comment.setFileHash(chunks[0]);
                comment.setCommentId(chunks[1]);
                comment.setNode(chunks[2]);
                comment.setTime(new Date(Long.valueOf(chunks[3])));
                comment.setComment(request.getData().substring(request.getData().lastIndexOf(chunks[3]) + 2,
                        request.getData().length()));
                commentService.saveComment(comment);
                BroadCastMessenger broadCastMessenger = new BroadCastMessenger();
                broadCastMessenger.broadcast(request);
            }
        }.start();
    }


    public void broadcastFileRating(final String fileHash, final String node, final int rating){
        new Thread(){
            @Override
            public void run() {
                LOGGER.debug("broadcasting file rating");
                MessageRequest request = new MessageRequest(UUID.randomUUID().toString(),
                        fileHash + " " + node + " " + rating,
                        "FILRTNG", 0, (int)PeerForm.spTtl.getValue(), NodeContext.getUserName());
                BroadCastMessenger broadCastMessenger = new BroadCastMessenger();
                broadCastMessenger.broadcast(request);
            }
        }.start();
    }

    public void handleReceivedFileRating(final MessageRequest request) {
        new Thread() {
            @Override
            public void run() {
                Comment comment = new Comment();
                String chunks[] = request.getData().split(" ");
                String fileHash = chunks[0];
                String node = chunks[1];
                int rating = Integer.valueOf(chunks[2]);
                fileRatingService.saveFileRating(fileHash, node, rating);
                BroadCastMessenger broadCastMessenger = new BroadCastMessenger();
                broadCastMessenger.broadcast(request);
            }
        }.start();
    }
}
