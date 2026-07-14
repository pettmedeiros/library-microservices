package com.peterson.userservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peterson.userservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    //método que gera automaticamente SELECT * FROM tb_users WHERE email = ?, optinal serve para 
    //evitar erro caso o email não exista no banco. 
    Optional<User> findByEmail (String email);

    
}
