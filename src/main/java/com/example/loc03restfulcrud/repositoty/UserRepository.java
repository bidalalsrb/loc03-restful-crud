package com.example.loc03restfulcrud.repositoty;

import com.example.loc03restfulcrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);


}
