package com.time_tracking.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.time_tracking.TimeTrackingApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@Configuration
public class FirebaseConfig {

   @PostConstruct
   public void initialize() throws IOException {
      ClassLoader classLoader = TimeTrackingApplication.class.getClassLoader();

      File file = new File(Objects.requireNonNull(
            classLoader.getResource("serviceAccountKey.json")).getFile());
      FileInputStream serviceAccount =
            new FileInputStream(file.getAbsoluteFile());

      FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://time-tracking-35fc0-default-rtdb.firebaseio.com/")
            .setProjectId("time-tracking-35fc0")
            .build();

      FirebaseApp.initializeApp(options);
   }
}
