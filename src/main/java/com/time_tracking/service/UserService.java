package com.time_tracking.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.time_tracking.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

   private CollectionReference getCollectionUsers() {
      Firestore dbFirestore = FirestoreClient.getFirestore();
      return dbFirestore.collection("users");
   }

   //CREATE - REGISTRATION USER
   public String registration(User user) throws ExecutionException, InterruptedException {
      ApiFuture<WriteResult> resultApiFuture = getCollectionUsers().document(user.getId()).set(user);
      return resultApiFuture.get().getUpdateTime().toString();
   }

   // READ - SORTED USERS
   public List<User> getAllUsersSortedByRating() throws ExecutionException, InterruptedException {
      ApiFuture<QuerySnapshot> querySnapshot = getCollectionUsers().get();
      QuerySnapshot snapshot = querySnapshot.get();
      List<User> users = new ArrayList<>();

      for (QueryDocumentSnapshot document : snapshot) {
         User user = document.toObject(User.class);
         users.add(user);
      }
      return users.stream().sorted(Comparator.comparing(User::getRating).reversed()).toList();
   }

   // READ - USER BY ID
   public User getUserById(String userId) throws ExecutionException, InterruptedException {
      DocumentReference documentReference = getCollectionUsers().document(userId);
      ApiFuture<DocumentSnapshot> future = documentReference.get();
      DocumentSnapshot documentSnapshot = future.get();

      User user;
      if (!documentSnapshot.exists()) {
         throw new InterruptedException("User not found");
      }
      user = documentSnapshot.toObject(User.class);
      return user;
   }

   // UPDATE - USER
   public String updateUser(String userId, User user) throws ExecutionException, InterruptedException {
      ApiFuture<WriteResult> collectionsApiFuture = getCollectionUsers().document(userId).set(user);
      return collectionsApiFuture.get().getUpdateTime().toString();
   }

   // DELETE - USER
   public String deleteUser(String userId) {
      getCollectionUsers().document(userId).delete();
      return "Successfully deleted " + userId;
   }
}
