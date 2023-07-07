package com.admin_authmicroservice.admin_authmicroservice.repositories;

import com.admin_authmicroservice.admin_authmicroservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {}
