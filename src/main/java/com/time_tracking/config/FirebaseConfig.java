package com.time_tracking.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.time_tracking.TimeTrackingApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@Configuration
@ConfigurationProperties(prefix = "firebase")
public class FirebaseConfig {

   @Value("${firebase.project.id}")
   String projectId;

   @Value("${firebase.database.url}")
   String databaseUrl;

   @Value("${firebase.service.account.path}")
   String accountPath;

   @Bean
   public FirebaseApp firebaseApp() throws IOException {
      ClassLoader classLoader = TimeTrackingApplication.class.getClassLoader();

      File file = new File(Objects.requireNonNull(
            classLoader.getResource(accountPath)).getFile());
      FileInputStream serviceAccount =
            new FileInputStream(file.getAbsoluteFile());

      FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl(databaseUrl)
            .setProjectId(projectId)
            .build();

      return FirebaseApp.initializeApp(options);
   }

   @Bean
   public FirebaseAuth firebaseAuth() throws IOException {
      return FirebaseAuth.getInstance(firebaseApp());
   }
}
