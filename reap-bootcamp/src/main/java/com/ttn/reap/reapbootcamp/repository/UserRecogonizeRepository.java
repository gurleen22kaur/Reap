package com.ttn.reap.reapbootcamp.repository;

import com.ttn.reap.reapbootcamp.entity.UserRecogonize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRecogonizeRepository extends CrudRepository<UserRecogonize,Integer> {

    //List<UserRecogonize> findAll();

    @Query("select u from UserRecogonize u order by u.timestamp desc")
    List<UserRecogonize> findRecogonize();

    @Query("select u from UserRecogonize u where u.sourceEmail=:sourceEmail")
    List<UserRecogonize> findShared(@Param("sourceEmail") String sourceEmail);


    @Query("select u from UserRecogonize u where u.destEmail=:destEmail")
    List<UserRecogonize> findReceived(@Param("destEmail") String destEmail);


    @Query("select u from UserRecogonize u where u.sourceEmail=:email or u.destEmail=:email")
    List<UserRecogonize> findAllBadges(@Param("email") String email);
}

