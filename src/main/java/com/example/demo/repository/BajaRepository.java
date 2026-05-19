package com.example.demo.repository;

import com.example.demo.model.Baja;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BajaRepository extends JpaRepository<Baja, Long> {
    List<Baja> findByActivoId(Long activoId);
}
