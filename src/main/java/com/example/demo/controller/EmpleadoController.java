package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Empleado;
import com.example.demo.model.Asignacion;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.repository.AsignacionRepository;

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
    public Empleado actualizar(@PathVariable String id, @RequestBody Empleado empleado) {
        empleado.setId(id);
        return repo.save(empleado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        repo.deleteById(id);
    }

    @GetMapping("/{id}/asignaciones")
    public ResponseEntity<List<Asignacion>> asignacionesPorEmpleado(@PathVariable String id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(asignacionRepo.findByEmpleadoId(id));
    }
}