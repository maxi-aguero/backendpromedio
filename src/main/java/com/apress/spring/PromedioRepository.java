package com.apress.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface PromedioRepository extends JpaRepository<PromedioEntity, Long> {
    Optional<PromedioEntity> findByFechadecreacion(Date fechadecreacion);

}
