/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.access;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import kbse_nkso_client.entities.Avatar;

/**
 *
 * @author nolde
 */
public class AvatarDTO implements Serializable {

    private Long id;
    private int imageHash;
    private String contentType;
    private byte[] image;

    /**
     *
     */
    public AvatarDTO() {
        this.id = -1L;
        this.contentType = "image/jpeg";
        this.imageHash = -1;
        this.image = new byte[1];
    }

    /**
     *
     * @param imageHash
     * @param contentType
     * @param image
     */
    public AvatarDTO(int imageHash, String contentType, byte[] image) {
        //this.id = 0L;
        this.imageHash = imageHash;
        this.contentType = contentType;
        this.image = image;
    }

    /**
     *
     * @param id
     * @param imageHash
     * @param contentType
     * @param image
     */
    public AvatarDTO(long id, int imageHash,String contentType, byte[] image) {
        this.id = id;
        this.imageHash = imageHash;
        this.contentType = contentType;
        this.image = image;
    }

    /**
     *
     * @param a
     * @return
     */
    public static AvatarDTO toAvatarDTO(Avatar a) {
        if (a == null) {
            return null;
        }
        return new AvatarDTO(a.getId(), a.getImageHash(), a.getContentType(),a.getImage());
    }

    /**
     *
     * @return
     */
    public Avatar toAvatar() {
        if (this.id == null) {
            return AvatarBuilder.create().imageHash(this.imageHash).contentType(this.contentType).image(this.image).build();
        } else {
            return AvatarBuilder.create().id(this.id).imageHash(this.imageHash).contentType(this.contentType).image(this.image).build();
        }
    }

    /**
     *
     * @return
     */
    public JsonObject toJsonObejct() {
        JsonObjectBuilder js = Json.createObjectBuilder();
        Gson gson = new Gson();
        String byteList = gson.toJson(this.image, byte[].class);
        if (this.id != null) {
            js.add("id", this.id);
        }

        js.add("imageHash", this.imageHash).add("image", byteList);
        return js.build();
    }

    /**
     *
     * @param js
     * @return
     */
    public static AvatarDTO toPOJO(JsonObject js) {
        AvatarDTO a = new AvatarDTO();
        Gson gson = new Gson();
        if(js.getJsonNumber("id")!= null)
        a.setId(js.getJsonNumber("id").longValue());
        a.setImageHash(js.getInt("imageHash"));
        a.setImage(gson.fromJson(js.getString("image"), new TypeToken<byte[]>() {
        }.getType()));
        return a;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
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
    public int getImageHash() {
        return imageHash;
    }

    /**
     *
     * @param imageHash
     */
    public void setImageHash(int imageHash) {
        this.imageHash = imageHash;
    }

    /**
     *
     * @return
     */
    public byte[] getImage() {
        return image;
    }

    /**
     *
     * @param image
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

}
