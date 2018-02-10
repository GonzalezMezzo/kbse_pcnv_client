/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.access;

import java.util.ArrayList;
import kbse_nkso_client.entities.Avatar;

/**
 *
 * @author nolde
 */
public class AvatarBuilder {
    private long id;
    private int imageHash;
    private ArrayList<Byte> image;

    private AvatarBuilder() {}
    
    public static AvatarBuilder create() {
        return new AvatarBuilder();
    }
    
    public AvatarBuilder id(long id) {
        this.id = id;
        return this;
    }
    
    public AvatarBuilder imageHash(int imageHash) {
        this.imageHash = imageHash;
        return this;
    }
    
    public AvatarBuilder image(ArrayList<Byte> image) {
        this.image = image;
        return this;
    }
    
    public Avatar build() {
        Avatar res = new Avatar();
        res.setId(id);
        res.setImage(image);
        res.setImageHash(imageHash);
        return res;
    }
}
