package com.example.demo.repository;

import com.example.demo.model.Baja;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class BajaRepository {

    private final Firestore db;

    public BajaRepository(Firestore db) {
        this.db = db;
    }

    private CollectionReference getCollection() {
        return db.collection("bajas");
    }

    public List<Baja> findAll() {
        List<Baja> lista = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = getCollection().get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot doc : documents) {
                Baja baja = doc.toObject(Baja.class);
                baja.setId(doc.getId());
                lista.add(baja);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Baja save(Baja baja) {
        CollectionReference col = getCollection();
        if (baja.getId() == null || baja.getId().isEmpty()) {
            // Crear nueva baja (Genera un ID automático en Firebase)
            DocumentReference docRef = col.document();
            baja.setId(docRef.getId());
            docRef.set(baja);
        } else {
            // Actualizar baja existente
            col.document(baja.getId()).set(baja);
        }
        return baja;
    }

    public void deleteById(String id) {
        getCollection().document(id).delete();
    }

    public List<Baja> findByActivoId(Long activoId) {
        List<Baja> lista = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = getCollection().whereEqualTo("activoId", activoId).get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot doc : documents) {
                Baja baja = doc.toObject(Baja.class);
                baja.setId(doc.getId());
                lista.add(baja);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return lista;
    }
}