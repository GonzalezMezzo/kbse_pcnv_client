/**
 * This file was generated by the Jeddict
 */
package kbse_nkso_client.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * @author nolde
 */
@Entity
public class Avatar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    @Basic
    private int imageHash;

    @Column(nullable = true)
    @Lob
    @Basic
    private ArrayList<Byte> image;

    //TODO: delete later
    public Avatar() {
        this.imageHash = -1;
        this.image = new ArrayList<>();
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

    public ArrayList<Byte> getImage() {
        return this.image;
    }

    public void setImage(ArrayList<Byte> image) {
        this.image = image;
    }

}