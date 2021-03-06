package com.example.projektkoncowy1.repository;

import com.example.projektkoncowy1.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
