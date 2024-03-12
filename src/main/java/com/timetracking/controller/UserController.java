package com.timetracking.controller;

import com.timetracking.domain.User;
import com.timetracking.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user")
public class UserController {

   public UserService userService;

   public UserController(UserService userService) {
      this.userService = userService;
   }

   // UPDATE - SET POINT
   @PatchMapping("/set-estimate")
   public ResponseEntity<String> setEstimateToUser(@RequestParam String userIdFrom,
                                                   @RequestParam String userIdTo,
                                                   @RequestParam Double estimate)
           throws ExecutionException, InterruptedException {

      userService.setEstimateToUser(userIdFrom, userIdTo, estimate);
      return ResponseEntity.ok("Estimate set successfully");
   }

   // READ - ALL USERS
   @GetMapping("/getAll")
   public List<User> getAllUsers() throws InterruptedException, ExecutionException {
      return userService.getAllUsers();
   }

   // CREATE - USER
   @PostMapping("/create")
   public User createUser(@RequestBody User user) throws InterruptedException, ExecutionException {
      return userService.createUser(user);
   }

   // READ - USER
   @GetMapping("/get")
   public User getUsers(@RequestParam String id) throws InterruptedException, ExecutionException {
      return userService.getUser(id);
   }

   // UPDATE - USER
   @PatchMapping("/update")
   public String updateUsers(@RequestBody User user) throws InterruptedException, ExecutionException {
      return userService.updateUser(user);
   }

   // DELETE - USER
   @DeleteMapping("/delete")
   public String deleteUsers(@RequestParam String id) throws InterruptedException, ExecutionException {
      return userService.deleteUser(id);
   }
}
