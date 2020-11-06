package com.anhtt.eTutor.repository;

import com.anhtt.eTutor.model.Role;
import com.anhtt.eTutor.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(RoleName roleName);
    
    List<Role> findByisDeletedFalse();
}