package com.chadment.chadment_rh.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Data
@Entity
public class Department {

    @Id
    @Column(name = "IdDept")
    private String idDept;

    @Column(name = "NomDept")
    private String nomDept;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public Department() {
    }

    public double masseSalariale(){
        return this.employees.stream().mapToDouble(Employee::getSalaire).sum();
    }
    public Department(String idDept, String nomDept) {
        this.idDept = idDept;
        this.nomDept = nomDept;
    }
}

