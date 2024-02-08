package com.chadment.chadment_rh.service;

import com.chadment.chadment_rh.model.Department;

import java.util.List;

public interface ServiceDep {
    public List<Department> listAll() ;

    public void saveDep(Department dep);

    public Department get(String id);

    public void delete(String id) throws Exception;

    public Long Count();
}
