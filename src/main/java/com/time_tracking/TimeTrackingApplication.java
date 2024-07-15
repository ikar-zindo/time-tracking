package com.time_tracking;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TimeTrackingApplication {

   public static void main(String[] args) {
      SpringApplication.run(TimeTrackingApplication.class, args);
   }
}
