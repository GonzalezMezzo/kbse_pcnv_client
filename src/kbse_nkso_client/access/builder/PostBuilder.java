/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.access.builder;


import java.io.Serializable;
import java.util.ArrayList;
import kbse_nkso_client.controller.ModelController;
import kbse_nkso_client.entities.Comment;
import kbse_nkso_client.entities.Post;
import kbse_nkso_client.entities.Rating;
import kbse_nkso_client.entities.SystemUser;

/**
 *
 * @author philippnienhuser
 */
public class PostBuilder implements Serializable {

    private long id;
    private String url;
    private String description;
    private ArrayList<Comment> comments;
    private SystemUser creatorId;
    private int totalRating;
    private ArrayList<Rating> ratings;

    private PostBuilder() {
    }

    public static PostBuilder create() {
        return new PostBuilder();
    }

    public PostBuilder id(long id) {
        this.id = id;
        return this;
    }

    public PostBuilder url(String url) {
        this.url = url;
        return this;
    }

    public PostBuilder comment(String comment) {
        this.description = comment;
        return this;
    }

    public PostBuilder creator(SystemUser creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public PostBuilder totalRating(int rating) {
        this.totalRating = rating;
        return this;
    }

    public PostBuilder ratings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
        return this;
    }

    public PostBuilder comments(ArrayList<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Post build() {
        Post res = new Post();
        res.setId(this.id);
        res.setUrl(this.url);
        res.setDescription(this.description);
        res.setComments(this.comments);
        res.setAuthor(this.creatorId);
        res.setTotalRating(this.totalRating);
        res.setRatings(this.ratings);
        return res;
    }
}
