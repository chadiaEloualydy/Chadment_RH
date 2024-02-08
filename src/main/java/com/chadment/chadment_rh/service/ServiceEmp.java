package com.chadment.chadment_rh.service;

import com.chadment.chadment_rh.model.Employee;

import java.util.List;

public interface ServiceEmp{
    public List<Employee> listAll() ;

    public void saveEmp(Employee emp);

    public void updateEmp(Integer id, Employee emp);

    long NbreTotalEmp();

    public Employee get(Integer id);

    public void delete(Integer id);
}
