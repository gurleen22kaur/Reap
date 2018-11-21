package com.ttn.reap.reapbootcamp.repository;

import com.ttn.reap.reapbootcamp.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.annotation.security.PermitAll;
import java.util.List;

public interface UserRepository extends CrudRepository<User,Integer> {

    public User findByEmail(String email);


    @Query("SELECT u from User u where u.email=:emailId")
    public List<User> findByEmailId(@Param("emailId") String emailId);




}
