/**
 * This file was generated by the Jeddict
 */
package kbse_nkso_client.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author nolde
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Avatar.findAll", query = "SELECT s FROM Avatar s")
    ,
    @NamedQuery(name = "Avatar.findByHash", query = "SELECT s FROM Avatar s WHERE s.imageHash = :hash")
})
public class Avatar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    @Basic
    private int imageHash;

    @Column(nullable = false)
    @Basic
    private String contentType;

    @Column(nullable = false)
    @Lob
    @Basic
    private byte[] image;

    public Avatar() {
        this.imageHash = -1;
        this.contentType = "image/jpeg";
        this.image = new byte[1];
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getImageHash() {
        return this.imageHash;
    }

    public void setImageHash(int imageHash) {
        this.imageHash = imageHash;
    }

    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] path) {
        this.image = path;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
