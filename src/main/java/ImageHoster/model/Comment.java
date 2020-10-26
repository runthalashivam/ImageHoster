package ImageHoster.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    //The 'comments' table is mapped to 'users' table with Many:One mapping
    //One comment can have only one user (owner) but one user can have multiple comments
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    //The 'comments' table is mapped to 'images' table with Many:One mapping
    //One comment can have only be associated with one image but one image can have multiple comments
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}