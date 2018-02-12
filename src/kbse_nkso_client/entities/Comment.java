/**
 * This file was generated by the Jeddict
 */
package kbse_nkso_client.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author nolde
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query= "SELECT s FROM Post s"),
    @NamedQuery(name = "Comment.findByUrlAndUsername", query= "SELECT s FROM Post s WHERE s.url= :url AND s.author = :username")
})
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String message;

    @Basic
    private String timestamp;

    @ManyToOne(targetEntity = SystemUser.class)
    private SystemUser author;

    //@ManyToOne(targetEntity = Post.class)
    //private Post post;

    /**
     *
     * @return
     */

    public Long getId() {
        return this.id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return this.message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     */
    public String getTimestamp() {
        return this.timestamp;
    }

    /**
     *
     * @param timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     *
     * @return
     */
    public SystemUser getAuthor() {
        return this.author;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(SystemUser author) {
        this.author = author;
    }

    /*public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    */
}