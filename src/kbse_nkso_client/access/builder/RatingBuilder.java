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
    
    public static RatingBuilder create() {
        return new RatingBuilder();
    }
    
    public RatingBuilder id(long id) {
        this.id = id;
        return this;
    }
    
    public RatingBuilder ratedValue(int ratedValue) {
        this.ratedValue = ratedValue;
        return this;
    }
    
    public RatingBuilder user(SystemUser user) {
        this.user = user;
        return this;
    }
    
    public RatingBuilder post(Post post) {
        this.post = post;
        return this;
    }
    
    public Rating build() {
        Rating res = new Rating();
        res.setId(this.id);
        res.setRatedValue(this.ratedValue);
        res.setUser(this.user);
        //res.setPost(this.post);
        return res;
    }
}
