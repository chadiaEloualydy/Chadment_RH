package com.chadment.chadment_rh.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEmp")
    private Integer idEmp;

    @Column(name = "NomEmp")
    private String nomEmp;

    @Column(name = "Salaire")
    private double salaire;

    @Column(name = "Age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "RefDept")
    private Department department;

    public Employee() {

    }
    public Employee(Integer idEmp, String nomEmp, double salaire, int age, Department department) {
        this.idEmp = idEmp;
        this.nomEmp = nomEmp;
        this.salaire = salaire;
        this.age = age;
        this.department = department;
    }

}

