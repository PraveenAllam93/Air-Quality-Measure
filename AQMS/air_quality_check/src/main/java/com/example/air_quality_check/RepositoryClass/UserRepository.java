package com.example.air_quality_check.RepositoryClass;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.air_quality_check.EntityClass.entity;
import com.example.air_quality_check.EntityClass.entityID;

/* JPArepository which manages data in this application, with respect to ID */
public interface UserRepository extends JpaRepository<entity, entityID> {
    
}
