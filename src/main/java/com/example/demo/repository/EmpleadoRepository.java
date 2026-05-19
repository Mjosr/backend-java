package com.example.demo.repository;

import com.example.demo.model.Empleado;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class EmpleadoRepository {
    private final Firestore db;

    public EmpleadoRepository(Firestore db) { this.db = db; }
    private CollectionReference getCollection() { return db.collection("empleados"); }

    public List<Empleado> findAll() {
        List<Empleado> lista = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = getCollection().get();
            for (QueryDocumentSnapshot doc : future.get().getDocuments()) {
                Empleado objeto = doc.toObject(Empleado.class);
                objeto.setId(doc.getId());
                lista.add(objeto);
            }
        } catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }
        return lista;
    }

    public Empleado save(Empleado objeto) {
        if (objeto.getId() == null || objeto.getId().isEmpty()) {
            DocumentReference docRef = getCollection().document();
            objeto.setId(docRef.getId());
            docRef.set(objeto);
        } else {
            getCollection().document(objeto.getId()).set(objeto);
        }
        return objeto;
    }

    public boolean existsById(String id) {
        try {
            return getCollection().document(id).get().get().exists();
        } catch (Exception e) { return false; }
    }

    public void deleteById(String id) { getCollection().document(id).delete(); }
}