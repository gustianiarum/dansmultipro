package com.danspro.danspro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationRepository extends JpaRepository<IdentificationRepository, String> {
    
}
