package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

}

//Crud hérite de jpa mais ici pas besoin de jpaRepo