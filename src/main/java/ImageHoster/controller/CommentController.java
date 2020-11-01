package ImageHoster.controller;


import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value="/image/{imageId}/{imageTitle}/comments", method= RequestMethod.POST)
    public String postComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, @RequestParam("comment") String comments, Model model, HttpSession session) {

        Comment newComment = new Comment();
        newComment.setDate(new Date());
        newComment.setText(comments);

        User user = (User) session.getAttribute("loggeduser");
        newComment.setUser(user);

        Image image = imageService.getImage(imageId);
        newComment.setImage(image);

        commentService.postComment(newComment);
        return "redirect:/images/" + imageId + "/" + imageTitle;
    }
}
