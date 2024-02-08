package com.chadment.chadment_rh.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "Email")
    private String Email;

    @Column(name = "Nom_User")
    private String nom_Complet;

    @Column(name = "Password")
    private String password;

}
