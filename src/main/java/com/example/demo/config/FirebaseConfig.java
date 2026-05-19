package com.example.demo.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Configuration
public class FirebaseConfig {

    @Bean
    public Firestore firestore() throws IOException {
        // Evita inicializar duplicados si Spring Boot recarga el contexto
        if (FirebaseApp.getApps().isEmpty()) {
            InputStream serviceAccount;

            // 1. Intentamos leer la variable de entorno (Para cuando esté en Render)
            String firebaseJson = System.getenv("FIREBASE_CREDENTIALS");

            if (firebaseJson != null && !firebaseJson.isEmpty()) {
                // Si la variable existe, convertimos el texto JSON en un stream de lectura
                serviceAccount = new ByteArrayInputStream(firebaseJson.getBytes(StandardCharsets.UTF_8));
            } else {
                // 2. Si no existe la variable, usamos tu archivo local (Para cuando estés en tu computadora)
                serviceAccount = new ClassPathResource("firebase-config.json").getInputStream();
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        }
        return FirestoreClient.getFirestore();
    }
}