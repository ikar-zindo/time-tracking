package com.timetracking.scheduler;

import com.timetracking.service.TopUserService;
import com.timetracking.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class UserScheduler {

   private final UserService userService;
   private final TopUserService topUserService;

   public UserScheduler(UserService userService,
                        TopUserService topUserService) {

      this.userService = userService;
      this.topUserService = topUserService;
   }

   @Scheduled(cron = "0 45 23 * * *")
   public void updateTopUsers()
           throws ExecutionException, InterruptedException {

      topUserService.updateTopUsers();
   }

   @Scheduled(cron = "0 0 0 * * *")
   public void addTop10Users()
           throws ExecutionException, InterruptedException {

      topUserService.addTop10Users();
   }
}
