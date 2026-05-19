package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Baja;
import com.example.demo.repository.BajaRepository;

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
    public Baja actualizar(@PathVariable String id, @RequestBody Baja baja) { // Cambiado a String
        baja.setId(id);
        return repo.save(baja);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) { // Cambiado a String
        repo.deleteById(id);
    }
}