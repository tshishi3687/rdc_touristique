package com.example.rdc_touristique.data_access.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "image_ville")
@Component
public class ImageVille {

    public ImageVille() {
        super();
    }
    public ImageVille(String name, String type, byte[] picByte, Ville ville) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.ville = ville;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;
    //image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column
    @Column(name = "picByte", length = 1000)
    @Lob
    private byte[] picByte;

    @ManyToOne
    private Ville ville;

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public byte[] getPicByte() {
        return picByte;
    }
    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

}
