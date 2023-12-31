package com.admin_authmicroservice.admin_authmicroservice.repositories;

import com.admin_authmicroservice.admin_authmicroservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
}
