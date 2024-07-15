package com.time_tracking.controller;

import com.time_tracking.entity.User;
import com.time_tracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

   private final UserService userService;

   // CREATE - USER
   @PostMapping("/registration")
   public String registrationUser(@RequestBody User user) throws ExecutionException, InterruptedException {
      return userService.registration(user);
   }

   // READ - USER
   @GetMapping
   public User getUsers(@RequestParam String userId) throws ExecutionException, InterruptedException {
      return userService.getUserById(userId);
   }

   // UPDATE - USER
   @PutMapping
   public String updateUsers(@RequestParam String userId,
                             @RequestBody User user) throws InterruptedException, ExecutionException {
      return userService.updateUser(userId, user);
   }

   // DELETE - USER
   @DeleteMapping
   public String deleteUsers(@RequestParam String userId) {
      return userService.deleteUser(userId);
   }
}
