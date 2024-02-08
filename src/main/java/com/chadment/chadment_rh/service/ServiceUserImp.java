package com.chadment.chadment_rh.service;

import com.chadment.chadment_rh.model.User;
import com.chadment.chadment_rh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUserImp implements ServiceUser {
    @Autowired
    UserRepository repo;
    @Override
    public List<User> listAll() {
        return repo.findAll();
    }

    @Override
    public void saveUser(User user) {
        repo.save(user);
    }

    @Override
    public User get(String email) {
        return repo.getById(email) ;
    }

    @Override
    public void delete(String email) {
            repo.deleteById(email);
    }
}
