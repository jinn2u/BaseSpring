package com.cos.blog.Repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}

//JAP naming 전략
//    User findByUsernameAndPassword(String username, String password);

//    @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//    User login(String username, String password);