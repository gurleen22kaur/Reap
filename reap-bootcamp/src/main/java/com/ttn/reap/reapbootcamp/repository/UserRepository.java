package com.ttn.reap.reapbootcamp.repository;

import com.ttn.reap.reapbootcamp.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {

    public User findByEmail(String email);

}
