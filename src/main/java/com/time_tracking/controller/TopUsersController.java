package com.time_tracking.controller;

import com.time_tracking.entity.User;
import com.time_tracking.service.TopUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/topUsers")
@RequiredArgsConstructor
public class TopUsersController {

   private final TopUserService topUserService;

   // UPDATE - TOP USERS
   @PutMapping("/updateTopUsers")
   public List<User> updateTopUsers() throws ExecutionException, InterruptedException {
      return topUserService.updateTopUsers();
   }
}
