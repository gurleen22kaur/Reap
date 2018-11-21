package com.ttn.reap.reapbootcamp.repository;

import com.ttn.reap.reapbootcamp.entity.Badge;
import com.ttn.reap.reapbootcamp.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BadgeRepository extends CrudRepository<Badge,Integer> {

    public List<Badge> findAllByBadge(String badge);

    @Query("SELECT b from Badge b where b.badge=:badge")
    public List<Badge> findByBadge(@Param("badge") String badge);

}
