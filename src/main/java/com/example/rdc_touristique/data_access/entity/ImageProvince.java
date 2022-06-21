package com.example.rdc_touristique.data_access.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "image_province")
@Component
public class ImageProvince extends BaseEntity{

    public ImageProvince() {
        super();
    }
    public ImageProvince(String name, String type, byte[] picByte, Province provinceID) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.provinceID = provinceID;
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
    private Province provinceID;

    public Province getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(Province provinceID) {
        this.provinceID = provinceID;
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
