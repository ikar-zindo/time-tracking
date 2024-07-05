package com.timetracking.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.timetracking.domain.User;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TopUserService {

   public final UserService userService;

   public TopUserService(UserService userService) {
      this.userService = userService;
   }

   // UPDATE - UPDATE TOP USERS
   public List<User> updateTopUsers()
           throws ExecutionException, InterruptedException {

      List<User> topUsers = userService.getAllUsers().stream()
              .sorted(Comparator.comparing(User::getRating).reversed())
              .toList();

      // CLEAR TOP USERS
      clearTopUsers();

      Firestore dbFirestore = FirestoreClient.getFirestore();

      for (User user : topUsers) {
         String userId = userService.generateUniqueId(dbFirestore);

         user.setId(userId);

         CollectionReference collectionReference = dbFirestore.collection("top_users");
         DocumentReference documentReference = collectionReference.document(user.getId());

         ApiFuture<WriteResult> future = documentReference.set(user);
      }

      return topUsers;
   }

   // CREATE - FILLING WITH DATA TOP USERS
   public List<User> addTop10Users()
           throws ExecutionException, InterruptedException {

      List<User> top10Users = userService.getAllUsers().stream()
              .sorted(Comparator.comparing(User::getRating).reversed())
              .limit(10)
              .toList();

      // CLEAR TOP USERS
      clearTopUsers();

      Firestore dbFirestore = FirestoreClient.getFirestore();

      for (User user : top10Users) {
         String userId = userService.generateUniqueId(dbFirestore);

         user.setId(userId);

         CollectionReference collectionReference = dbFirestore.collection("top_users");
         DocumentReference documentReference = collectionReference.document(user.getId());

         ApiFuture<WriteResult> future = documentReference.set(user);
      }

      return top10Users;
   }

   // DELETE - CLEAR TOP USERS
   public void clearTopUsers()
           throws ExecutionException, InterruptedException {

      Firestore dbFirestore = FirestoreClient.getFirestore();
      ApiFuture<QuerySnapshot> querySnapshotApiFuture = dbFirestore.collection("top_users").get();
      QuerySnapshot snapshot = querySnapshotApiFuture.get();

      for (QueryDocumentSnapshot document : snapshot) {
         String userId = document.getId();
         dbFirestore.collection("top_users").document(userId).delete();
      }
   }
}
