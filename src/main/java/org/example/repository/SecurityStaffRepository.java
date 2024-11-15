package org.example.repository;

import org.example.entity.SecurityStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityStaffRepository extends JpaRepository<SecurityStaff, Long> {
    Optional<SecurityStaff> findByFullName(String fullName);
}
