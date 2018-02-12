/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.access;

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
    private ModelController ctrl;

    private PostBuilder() {
    }

    /**
     *
     * @return
     */
    public static PostBuilder create() {
        return new PostBuilder();
    }

    /**
     *
     * @param id
     * @return
     */
    public PostBuilder id(long id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @param url
     * @return
     */
    public PostBuilder url(String url) {
        this.url = url;
        return this;
    }

    /**
     *
     * @param comment
     * @return
     */
    public PostBuilder comment(String comment) {
        this.description = comment;
        return this;
    }

    /**
     *
     * @param creatorId
     * @return
     */
    public PostBuilder creator(SystemUser creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    /**
     *
     * @param rating
     * @return
     */
    public PostBuilder totalRating(int rating) {
        this.totalRating = rating;
        return this;
    }

    /**
     *
     * @param ratings
     * @return
     */
    public PostBuilder ratings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
        return this;
    }

    /**
     *
     * @param comments
     * @return
     */
    public PostBuilder comments(ArrayList<Comment> comments) {
        this.comments = comments;
        return this;
    }

    /**
     *
     * @return
     */
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
