package com.timetracking.scheduler;

import com.timetracking.service.TopUserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class UserScheduler {
   private final TopUserService topUserService;

   public UserScheduler(TopUserService topUserService) {
      this.topUserService = topUserService;
   }

   // UPDATE TOP USERS AT 20:00
   @Scheduled(cron = "0 00 20 * * *")
   public void updateTopUsers()
           throws ExecutionException, InterruptedException {

      topUserService.updateTopUsers();
   }

   // UPDATE TOP 10 USERS AT 00:00
   @Scheduled(cron = "0 0 0 * * *")
   public void addTop10Users()
           throws ExecutionException, InterruptedException {

      topUserService.addTop10Users();
   }
}
