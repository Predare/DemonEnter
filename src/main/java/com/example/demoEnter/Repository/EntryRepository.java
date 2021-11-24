package com.example.demoEnter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoEnter.Entity.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer> {
    
}
