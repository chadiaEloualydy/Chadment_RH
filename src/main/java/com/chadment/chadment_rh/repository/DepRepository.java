package com.chadment.chadment_rh.repository;

import com.chadment.chadment_rh.model.Department;
import com.chadment.chadment_rh.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepRepository extends JpaRepository<Department,String> {
    Department findDepartmentByIdDeptOrNomDept(String idDept, String nomDept);

    @Query("SELECT d FROM Department d JOIN d.employees e GROUP BY d.idDept ORDER BY COUNT(e) DESC")
    List<Department> findDepartmentWithMaxEmployees();
    Department findByNomDept(String Nom);

}
