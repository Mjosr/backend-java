package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Activo;
import com.example.demo.model.Asignacion;
import com.example.demo.repository.ActivoRepository;
import com.example.demo.repository.AsignacionRepository;

@RestController
@RequestMapping("/api/activos")
@CrossOrigin("*")
public class ActivoController {

    private final ActivoRepository repo;
    private final AsignacionRepository asignacionRepo;

    public ActivoController(ActivoRepository repo, AsignacionRepository asignacionRepo) {
        this.repo = repo;
        this.asignacionRepo = asignacionRepo;
    }

    @GetMapping
    public List<Activo> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Activo guardar(@RequestBody Activo activo) {
        return repo.save(activo);
    }

    @PutMapping("/{id}")
    public Activo actualizar(@PathVariable String id, @RequestBody Activo activo) {
        activo.setId(id);
        return repo.save(activo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        repo.deleteById(id);
    }

    @GetMapping("/{id}/asignaciones")
    public ResponseEntity<List<Asignacion>> asignacionesPorActivo(@PathVariable String id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(asignacionRepo.findByActivoId(id));
    }
}