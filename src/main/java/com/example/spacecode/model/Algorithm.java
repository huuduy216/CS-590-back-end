package com.example.spacecode.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Algorithm")
public class Algorithm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlgorithm")
    private int idAlgorithm;

    @Column(name = "name")
    private String name;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "Identitykey")
    private String Identitykey;

    @Column(name = "desription")
    private String desription;

    public Algorithm() {
    }

    public Algorithm(String name, String subtitle, String identitykey, String desription) {
        this.name = name;
        this.subtitle = subtitle;
        Identitykey = identitykey;
        this.desription = desription;
    }

    public int getIdAlgorithm() {
        return idAlgorithm;
    }

    public void setIdAlgorithm(int idAlgorithm) {
        this.idAlgorithm = idAlgorithm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getIdentitykey() {
        return Identitykey;
    }

    public void setIdentitykey(String identitykey) {
        Identitykey = identitykey;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }
}
