package com.example.demo.controller;

import com.example.demo.model.Baja;
import com.example.demo.repository.BajaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bajas")
@CrossOrigin("*")
public class BajaController {

    private final BajaRepository repo;

    public BajaController(BajaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Baja> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Baja guardar(@RequestBody Baja baja) {
        return repo.save(baja);
    }

    @PutMapping("/{id}")
    public Baja actualizar(@PathVariable Long id, @RequestBody Baja baja) {
        baja.setId(id);
        return repo.save(baja);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
