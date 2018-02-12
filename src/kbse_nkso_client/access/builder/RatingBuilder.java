/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.access.builder;



import java.io.Serializable;
import kbse_nkso_client.entities.Post;
import kbse_nkso_client.entities.Rating;
import kbse_nkso_client.entities.SystemUser;

/**
 *
 * @author nolde
 */
public class RatingBuilder implements Serializable {
    
    private long id;
    private int ratedValue;
    private SystemUser user;
    private Post post;

    private RatingBuilder() {
    }
    
    /**
     *
     * @return
     */
    public static RatingBuilder create() {
        return new RatingBuilder();
    }
    
    /**
     *
     * @param id
     * @return
     */
    public RatingBuilder id(long id) {
        this.id = id;
        return this;
    }
    
    /**
     *
     * @param ratedValue
     * @return
     */
    public RatingBuilder ratedValue(int ratedValue) {
        this.ratedValue = ratedValue;
        return this;
    }
    
    /**
     *
     * @param user
     * @return
     */
    public RatingBuilder user(SystemUser user) {
        this.user = user;
        return this;
    }
    
    /**
     *
     * @param post
     * @return
     */
    public RatingBuilder post(Post post) {
        this.post = post;
        return this;
    }
    
    /**
     *
     * @return
     */
    public Rating build() {
        Rating res = new Rating();
        res.setId(this.id);
        res.setRatedValue(this.ratedValue);
        res.setUser(this.user);
        //res.setPost(this.post);
        return res;
    }
}
