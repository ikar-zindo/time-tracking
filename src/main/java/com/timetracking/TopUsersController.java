package com.timetracking;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/topUsers")
public class TopUsersController {

   private final TopUserService topUserService;

   public TopUsersController(TopUserService topUserService) {
      this.topUserService = topUserService;
   }

   @DeleteMapping("/clear")
   public void clearTopUsers() throws InterruptedException, ExecutionException {
      topUserService.clearTopUsers();
   }

   // CREATE
   @PostMapping("/addTop10Users")
   public List<User> createUser() throws InterruptedException, ExecutionException {
      return topUserService.addTop10Users();
   }
}
