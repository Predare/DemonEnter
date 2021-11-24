package com.example.demoEnter.Repository;

import java.util.Optional;

import com.example.demoEnter.Entity.UserGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepo extends JpaRepository<UserGroup, Integer>  {

    Optional<UserGroup> findByName(String name);
    
}
