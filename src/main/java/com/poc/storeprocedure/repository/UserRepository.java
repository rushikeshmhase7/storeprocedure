package com.poc.storeprocedure.repository;

import com.poc.storeprocedure.entity.BNYUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<BNYUser,Long> {

//    @Procedure(name = "countUsersByLastName")
    @Procedure(procedureName = "countByLast")
    public Long countUsersByLastName(String lastName);
}
