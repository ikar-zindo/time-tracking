package com.timetracking.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.timetracking.domain.User;
import com.timetracking.exception.UserException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

   //CREATE - USER
   public User createUser(User user)
           throws ExecutionException, InterruptedException {

      Firestore dbFirestore = FirestoreClient.getFirestore();
      String userId = generateUniqueId(dbFirestore);

      user.setId(userId);
      user.setRating(0.0);
      user.setEstimates(Collections.emptyMap());
      user.setIsBlocked(false);

      ApiFuture<WriteResult> collectionsApiFuture =
              dbFirestore.collection("users").document(user.getId()).set(user);

      return user;
   }

   // READ - USERS
   public List<User> getAllUsers()
           throws ExecutionException, InterruptedException {

      Firestore dbFirestore = FirestoreClient.getFirestore();
      ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection("users").get();

      QuerySnapshot snapshot = querySnapshot.get();
      List<User> users = new ArrayList<>();

      for (QueryDocumentSnapshot document : snapshot) {
         User user = document.toObject(User.class);
         users.add(user);
      }

      return users;
   }

   // READ - USER
   public User getUser(String id)
           throws ExecutionException, InterruptedException {

      Firestore dbFirestore = FirestoreClient.getFirestore();
      DocumentReference documentReference = dbFirestore.collection("users").document(id);

      ApiFuture<DocumentSnapshot> future = documentReference.get();
      DocumentSnapshot documentSnapshot = future.get();

      User user;

      if (documentSnapshot.exists()) {
         user = documentSnapshot.toObject(User.class);
         return user;
      }

      return null;
   }

   // UPDATE - USER
   public String updateUser(User user)
           throws ExecutionException, InterruptedException {

      Firestore dbFirestore = FirestoreClient.getFirestore();
      ApiFuture<WriteResult> collectionsApiFuture =
              dbFirestore.collection("users").document(user.getId()).set(user);

      return collectionsApiFuture.get().getUpdateTime().toString();
   }

   // UPDATE - SET ESTIMATE
   public void setEstimateToUser(String userIdFrom, String userIdTo, Double estimate)
           throws ExecutionException, InterruptedException {

      Firestore dbFirestore = FirestoreClient.getFirestore();
      DocumentReference documentReference = dbFirestore.collection("users").document(userIdTo);

      ApiFuture<DocumentSnapshot> future = documentReference.get();
      DocumentSnapshot documentSnapshot = future.get();

      if (documentSnapshot.exists()) {
         User user = documentSnapshot.toObject(User.class);

         Map<String, Double> estimates = user.getEstimates();

         if (estimates == null) {
            estimates = new HashMap<>();
         }

         estimates.put(userIdFrom, estimate);
         updateUserRating(userIdTo);

         WriteBatch batch = dbFirestore.batch();
         batch.update(documentReference, "estimates", estimates);
         batch.commit().get();

      } else {
         throw new UserException("User not found!");
      }
   }

   // UPDATE - USER RATING
   public Double updateUserRating(String userId)
           throws ExecutionException, InterruptedException {

      Firestore dbFireStore = FirestoreClient.getFirestore();
      DocumentReference documentReference = dbFireStore.collection("users").document(userId);

      ApiFuture<DocumentSnapshot> future = documentReference.get();
      DocumentSnapshot documentSnapshot = future.get();

      if (documentSnapshot.exists()) {
         User user = documentSnapshot.toObject(User.class);

         Map<String, Double> estimates = user.getEstimates();

         if (estimates != null) {
            Integer estimatesSize = estimates.size();
            Double estimatesSum = estimates.values().stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();

            Double rating = estimatesSum / estimatesSize;
            user.setRating(rating);
            documentReference.set(user);

            return rating;

         } else {
            throw new UserException("Estimates not found!");
         }
      } else {
         throw new UserException("User nit found!");
      }
   }

   // DELETE - USER
   public String deleteUser(String userId) {
      Firestore dbFirestore = FirestoreClient.getFirestore();
      ApiFuture<WriteResult> writeResult = dbFirestore.collection("user_name").document(userId).delete();

      return "Successfully deleted " + userId;
   }

   // ==================================================================================================================

   // GENERATE UUID
   private String generateRandomId() {
      return java.util.UUID.randomUUID().toString();
   }

   public String generateUniqueId(Firestore dbFirestore)
           throws ExecutionException, InterruptedException {

      String userId;
      do userId = generateRandomId();
      while (userIdExists(dbFirestore, userId));

      return userId;
   }

   // CHECK UUID IN DB
   private boolean userIdExists(Firestore dbFirestore, String userId)
           throws ExecutionException, InterruptedException {

      return dbFirestore.collection("users").document(userId).get().get().exists();
   }
}
