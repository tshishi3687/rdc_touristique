package com.example.rdc_touristique.data_access.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "imagesBien")
@Component
public class ImageBien {
    public ImageBien() {
        super();
    }
    public ImageBien(String name, String type, byte[] picByte, Bien bienid) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.bienid = bienid;
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
    private Bien bienid = new Bien();

    public Bien getBienid() {
        return bienid;
    }

    public void setBienid(Bien bienid) {
        this.bienid = bienid;
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
