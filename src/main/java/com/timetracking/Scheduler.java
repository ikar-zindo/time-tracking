package com.timetracking;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class Scheduler {

   private final UserService userService;
   private final TopUserService topUserService;

   public Scheduler(UserService userService,
                    TopUserService topUserService) {

      this.userService = userService;
      this.topUserService = topUserService;
   }

   @Scheduled(cron = "0 0 23 * * *")
   public void updateTopUsers()
           throws ExecutionException, InterruptedException {

      userService.updateTopUsers();
   }

   @Scheduled(cron = "0 0 0 * * *")
   public void addTop10Users()
           throws ExecutionException, InterruptedException {

      topUserService.addTop10Users();
   }
}
