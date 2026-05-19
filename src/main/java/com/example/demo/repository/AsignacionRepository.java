package com.example.demo.repository;

import com.example.demo.model.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
    List<Asignacion> findByEmpleadoId(Long empleadoId);
    List<Asignacion> findByActivoId(Long activoId);
}
