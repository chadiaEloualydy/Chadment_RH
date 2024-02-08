package com.chadment.chadment_rh.service;

import com.chadment.chadment_rh.model.Employee;
import com.chadment.chadment_rh.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEmpImp implements ServiceEmp{
    @Autowired
    EmpRepository repo;
    @Override
    public List<Employee> listAll() {
        return repo.findAll();
    }

    @Override
    public void saveEmp(Employee emp) {
        repo.save(emp);
    }

    @Override
    public long NbreTotalEmp(){ return repo.count();}

    @Override
    public Employee get(Integer id) {
        return repo.getById(id);
    }

    @Override
    public void updateEmp(Integer id, Employee emp){
        Employee newemp = repo.getById(id);
        newemp.setNomEmp(emp.getNomEmp());
        newemp.setAge(emp.getAge());
        newemp.setSalaire(emp.getSalaire());
        newemp.setDepartment(emp.getDepartment());
        repo.delete(emp);
        repo.save(newemp);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public Employee getByNom(String employeeInput) {
        return repo.findByNomEmp(employeeInput);
    }

}
