package com.chadment.chadment_rh.repository;

import com.chadment.chadment_rh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
