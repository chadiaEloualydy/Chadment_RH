package com.chadment.chadment_rh.service;

import com.chadment.chadment_rh.model.Department;
import com.chadment.chadment_rh.model.Employee;
import com.chadment.chadment_rh.repository.DepRepository;
import com.chadment.chadment_rh.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceDepImp implements ServiceDep{

    @Autowired
    DepRepository repo;
    @Autowired
    EmpRepository repoEmp ;
    @Override
    public List<Department> listAll() {
        return repo.findAll();
    }

    @Override
    public void saveDep(Department dep) {
        repo.save(dep);
    }

    @Override
    public Department get(String id) {
        return repo.getById(id) ;
    }

    @Override
    public void delete(String id) {
        List<Employee> employeesOfDep = get(id).getEmployees();
        if (employeesOfDep.isEmpty()){
            repo.deleteById(id);
        }
        else{
            Department autre = repo.save(new Department("autre","autre"));
            for (Employee emp : employeesOfDep){
                emp.setDepartment(autre);
            }
            repo.deleteById(id);
        }
    }

    @Override
    public Long Count(){
        return repo.count();
    }

    public Department plusNbrEmp(){
        List<Department> deps =repo.findDepartmentWithMaxEmployees();
        Department dep = deps.get(0);
        return dep;
    }

    public List<Employee> getEmployeesByDepartment(String departmentInput) {
        return repo.findDepartmentByIdDeptOrNomDept(departmentInput,departmentInput).getEmployees() ;
    }

    public boolean checkIfIdExists(String idDept) {
        return repo.existsById(idDept);
    }
}
