package com.example.springdemo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springdemo.data.database;

@Repository
public interface repo extends MongoRepository<database, String> {
    
    @Query("{name:'?0'}")
    database findItemByName(String name);
    
    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<database> findAll(String category);
    
    public long count();

}