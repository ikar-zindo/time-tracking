package com.time_tracking.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.time_tracking.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class AuthService {

   private final FirebaseAuth firebaseAuth;

   private final UserService userService;

   public FirebaseToken verifyIdToken(String idToken) throws FirebaseAuthException {
      return firebaseAuth.verifyIdToken(idToken);
   }

   public String getUserIdFromToken(String idToken) throws FirebaseAuthException {
      FirebaseToken decodedToken = verifyIdToken(idToken);
      return decodedToken.getUid();
   }

   public User getUserFromToken(String idToken) throws FirebaseAuthException, ExecutionException, InterruptedException {
      String userId = getUserIdFromToken(idToken);
      return userService.getUserById(userId);
   }
}
