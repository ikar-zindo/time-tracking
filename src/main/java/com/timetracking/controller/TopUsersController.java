package com.timetracking.controller;

import com.timetracking.domain.User;
import com.timetracking.service.TopUserService;
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

   // UPDATE - TOP USERS
   @PutMapping("/updateTopUsers")
   public List<User> updateTopUsers() throws ExecutionException, InterruptedException {
      return topUserService.updateTopUsers();
   }

   // DELETE - CLEAR TOP USERS
   @DeleteMapping("/clear")
   public void clearTopUsers() throws InterruptedException, ExecutionException {
      topUserService.clearTopUsers();
   }

   // CREATE - FILLING WITH DATA TOP USERS
   @PostMapping("/addTop10Users")
   public List<User> createUser() throws InterruptedException, ExecutionException {
      return topUserService.addTop10Users();
   }
}
