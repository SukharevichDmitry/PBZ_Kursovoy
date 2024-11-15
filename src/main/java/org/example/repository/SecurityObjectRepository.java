package org.example.repository;

import org.example.entity.SecurityObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityObjectRepository extends JpaRepository<SecurityObject, Long> {
}
