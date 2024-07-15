package com.time_tracking.service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.time_tracking.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TopUserService {

   public final UserService userService;

   public TopUserService(UserService userService) {
      this.userService = userService;
   }

   // UPDATE - UPDATE TOP USERS
   public List<User> updateTopUsers() throws ExecutionException, InterruptedException {
      List<User> allUsersSortedByRating = userService.getAllUsersSortedByRating();

      List<User> topTenUsers = allUsersSortedByRating.subList(0, Math.min(10, allUsersSortedByRating.size()));

      // UPDATE - TOP 10 USERS IN RTDB
      DatabaseReference topUsersRef = FirebaseDatabase.getInstance().getReference("top_users");
      topUsersRef.removeValueAsync();

      for (int i = 0; i < topTenUsers.size(); i++) {
         topUsersRef.child(String.valueOf(i + 1)).setValueAsync(topTenUsers.get(i));
      }
      return topTenUsers;
   }
}
