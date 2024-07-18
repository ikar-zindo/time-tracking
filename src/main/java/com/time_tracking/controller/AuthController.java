package com.time_tracking.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

   @PostMapping("/verifyToken")
   public String verifyToken(@RequestBody String idToken) throws FirebaseAuthException {
      FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
      return decodedToken.getUid();
   }
}
