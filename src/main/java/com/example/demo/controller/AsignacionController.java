package com.example.demo.controller;

import com.example.demo.model.Asignacion;
import com.example.demo.repository.AsignacionRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/asignaciones")
@CrossOrigin("*")
public class AsignacionController {

    private final AsignacionRepository repo;

    public AsignacionController(AsignacionRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Asignacion> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Asignacion guardar(@RequestBody Asignacion asignacion) {
        return repo.save(asignacion);
    }

    @PutMapping("/{id}")
    public Asignacion actualizar(@PathVariable Long id, @RequestBody Asignacion asignacion) {
        asignacion.setId(id);
        return repo.save(asignacion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
