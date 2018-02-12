/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.access.builder;

import java.util.ArrayList;
import kbse_nkso_client.entities.Avatar;

/**
 *
 * @author nolde
 */
public class AvatarBuilder {
    private long id;
    private int imageHash;
    private String contentType;
    private byte[] imagePath;

    private AvatarBuilder() {}
    
    /**
     *
     * @return
     */
    public static AvatarBuilder create() {
        return new AvatarBuilder();
    }
    
    /**
     *
     * @param id
     * @return
     */
    public AvatarBuilder id(long id) {
        this.id = id;
        return this;
    }
    
    /**
     *
     * @param imageHash
     * @return
     */
    public AvatarBuilder imageHash(int imageHash) {
        this.imageHash = imageHash;
        return this;
    }
    
    /**
     *
     * @param contentType
     * @return
     */
    public AvatarBuilder contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }
    
    /**
     *
     * @param image
     * @return
     */
    public AvatarBuilder image(byte[] image) {
        this.imagePath = image;
        return this;
    }
    
    /**
     *
     * @return
     */
    public Avatar build() {
        Avatar res = new Avatar();
        res.setId(id);
        res.setImage(imagePath);
        res.setImageHash(imageHash);
        return res;
    }

    
}
