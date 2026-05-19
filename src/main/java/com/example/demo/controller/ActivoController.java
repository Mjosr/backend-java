package com.example.demo.controller;

import com.example.demo.model.Activo;
import com.example.demo.model.Asignacion;
import com.example.demo.model.Baja;
import com.example.demo.repository.ActivoRepository;
import com.example.demo.repository.AsignacionRepository;
import com.example.demo.repository.BajaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/activos")
@CrossOrigin("*")
public class ActivoController {

    private final ActivoRepository repo;
    private final AsignacionRepository asignacionRepo;
    private final BajaRepository bajaRepo;

    public ActivoController(ActivoRepository repo,
                            AsignacionRepository asignacionRepo,
                            BajaRepository bajaRepo) {
        this.repo = repo;
        this.asignacionRepo = asignacionRepo;
        this.bajaRepo = bajaRepo;
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
    public Activo actualizar(@PathVariable Long id, @RequestBody Activo activo) {
        activo.setId(id);
        return repo.save(activo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repo.deleteById(id);
    }

    // Sub-recurso: asignaciones de un activo
    @GetMapping("/{id}/asignaciones")
    public ResponseEntity<List<Asignacion>> asignacionesPorActivo(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(asignacionRepo.findByActivoId(id));
    }

    // Sub-recurso: bajas de un activo
    @GetMapping("/{id}/bajas")
    public ResponseEntity<List<Baja>> bajasPorActivo(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bajaRepo.findByActivoId(id));
    }
}
