package com.time_tracking.utils;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class UuidGenerator {
   private static final Firestore dbFirestore = FirestoreClient.getFirestore();

   private static String generateRandomId() {
      return UUID.randomUUID().toString();
   }

   public static String generateUniqueId()
         throws ExecutionException, InterruptedException {

      String userId;
      do userId = generateRandomId();
      while (userIdExists(dbFirestore, userId));

      return userId;
   }

   // CHECK UUID IN DB
   private static boolean userIdExists(Firestore dbFirestore, String userId)
         throws ExecutionException, InterruptedException {

      return dbFirestore.collection("users").document(userId).get().get().exists();
   }

   // ========================================================================================

   public static UUID generate() {
      long currTimeMillis = System.currentTimeMillis();
      return concatUUIDAndTime(currTimeMillis, UUID.randomUUID());
   }

   private static UUID concatUUIDAndTime(long currTimeMillis, UUID uuid) {
      String millisHex = Long.toHexString(currTimeMillis);

      String uuidStr = uuid.toString().replace("-", "").substring(0, 16);

      String concatenated = String.format("%016x%s", Long.parseLong(millisHex, 16), uuidStr);
      String concatenatedWithDashes = concatenated.substring(0, 8) + "-" +
            concatenated.substring(8, 12) + "-" +
            concatenated.substring(12, 16) + "-" +
            concatenated.substring(16, 20) + "-" +
            concatenated.substring(20);

      return UUID.fromString(concatenatedWithDashes);
   }
}
