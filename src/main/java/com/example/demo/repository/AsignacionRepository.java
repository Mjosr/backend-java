package com.example.demo.repository;

import com.example.demo.model.Asignacion;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class AsignacionRepository {
    private final Firestore db;

    public AsignacionRepository(Firestore db) { this.db = db; }
    private CollectionReference getCollection() { return db.collection("asignaciones"); }

    public List<Asignacion> findAll() {
        List<Asignacion> lista = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = getCollection().get();
            for (QueryDocumentSnapshot doc : future.get().getDocuments()) {
                Asignacion objeto = doc.toObject(Asignacion.class);
                objeto.setId(doc.getId());
                lista.add(objeto);
            }
        } catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }
        return lista;
    }

    public Asignacion save(Asignacion objeto) {
        if (objeto.getId() == null || objeto.getId().isEmpty()) {
            DocumentReference docRef = getCollection().document();
            objeto.setId(docRef.getId());
            docRef.set(objeto);
        } else {
            getCollection().document(objeto.getId()).set(objeto);
        }
        return objeto;
    }

    public void deleteById(String id) { getCollection().document(id).delete(); }

    public List<Asignacion> findByEmpleadoId(String empleadoId) {
        List<Asignacion> lista = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = getCollection().whereEqualTo("empleadoId", empleadoId).get();
            for (QueryDocumentSnapshot doc : future.get().getDocuments()) {
                Asignacion objeto = doc.toObject(Asignacion.class);
                objeto.setId(doc.getId());
                lista.add(objeto);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    public List<Asignacion> findByActivoId(String activoId) {
        List<Asignacion> lista = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = getCollection().whereEqualTo("activoId", activoId).get();
            for (QueryDocumentSnapshot doc : future.get().getDocuments()) {
                Asignacion objeto = doc.toObject(Asignacion.class);
                objeto.setId(doc.getId());
                lista.add(objeto);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
}