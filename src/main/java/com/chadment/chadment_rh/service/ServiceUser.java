package com.chadment.chadment_rh.service;

import com.chadment.chadment_rh.model.Department;
import com.chadment.chadment_rh.model.User;

import java.util.List;

public interface ServiceUser {

    public List<User> listAll() ;

    public void saveUser(User user);

    public User get(String email);

    public void delete(String email) throws Exception;

}
