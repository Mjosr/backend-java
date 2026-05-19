package com.example.demo.controller;

import com.example.demo.model.Asignacion;
import com.example.demo.model.Empleado;
import com.example.demo.repository.AsignacionRepository;
import com.example.demo.repository.EmpleadoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin("*")
public class EmpleadoController {

    private final EmpleadoRepository repo;
    private final AsignacionRepository asignacionRepo;

    public EmpleadoController(EmpleadoRepository repo, AsignacionRepository asignacionRepo) {
        this.repo = repo;
        this.asignacionRepo = asignacionRepo;
    }

    @GetMapping
    public List<Empleado> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Empleado guardar(@RequestBody Empleado empleado) {
        return repo.save(empleado);
    }

    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        empleado.setId(id);
        return repo.save(empleado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repo.deleteById(id);
    }

    // Sub-recurso: asignaciones de un empleado
    @GetMapping("/{id}/asignaciones")
    public ResponseEntity<List<Asignacion>> asignacionesPorEmpleado(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(asignacionRepo.findByEmpleadoId(id));
    }
}
