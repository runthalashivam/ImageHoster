package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    //This controller method is called when the request pattern is of type '/image/{imageId}/{imageTitle}/comments' and also the incoming request is of POST type
    //The method receives all the details of the comment to be stored in the database, and now the comment will be sent to the business logic to be persisted in the database
    //After you get the comment, set the user of the comment by getting the logged in user from the Http Session
    //Set the date on which the comment is posted
    //After storing the comment, this method directs to the image on which comment was posted
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String addComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, @RequestParam("comment") String commentText, HttpSession session) {
        User user = (User) session.getAttribute("loggeduser");
        Comment comment = new Comment();
        comment.setImage(imageService.getImage(imageId));
        comment.setText(commentText);
        comment.setUser(user);
        comment.setCreatedDate(LocalDate.now());
        commentService.addComment(comment);
        return "redirect:/images/" + imageId + "/" + imageTitle;
    }
}