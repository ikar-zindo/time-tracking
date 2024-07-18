package com.time_tracking.scheduler;

import com.time_tracking.service.TopUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class TopUserScheduler {

   private final TopUserService topUserService;

   // UPDATE TOP USERS AT 20:00
   @Scheduled(cron = "0 56 13 * * *")
   public void updateTopUsers() throws ExecutionException, InterruptedException {
      topUserService.updateTopUsers();
   }
}
