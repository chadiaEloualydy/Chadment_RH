package com.chadment.chadment_rh.repository;

import com.chadment.chadment_rh.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpRepository extends JpaRepository<Employee,Integer> {
    Employee findByNomEmp(String nomEmp);
    List<Employee> findByDepartment_IdDept(String id);
}
