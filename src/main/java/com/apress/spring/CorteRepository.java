package com.apress.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorteRepository extends JpaRepository<CorteEntity, Long> {
}