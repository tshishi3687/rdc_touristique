package com.example.rdc_touristique.data_access.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "doc_officiel")
@Component
public class DocOfficielPersonne extends BaseEntity{

    public DocOfficielPersonne() {
        super();
    }
    public DocOfficielPersonne(String name, String type, byte[] picByte, Personne personneId) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.personneId = personneId;
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
    private Personne personneId;

    public Personne getPersonneId() {
        return personneId;
    }

    public void setPersonneId(Personne personneId) {
        this.personneId = personneId;
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
