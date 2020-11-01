package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void postComment(Comment comment) {
        commentRepository.postComment(comment);
    }

    public List<Comment> getAllImageComments(Image image) {
        return commentRepository.getAllImageComments(image);
    }
}
