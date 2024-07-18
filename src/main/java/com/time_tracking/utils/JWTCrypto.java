package com.time_tracking.utils;

import com.auth0.jwt.algorithms.Algorithm;

import java.security.SecureRandom;
import java.util.Base64;

public class JWTCrypto {

   public static void main(String[] args) {
      String accessTokenKey = generateRandomKey((byte) 32);
      System.out.println("Access token key: " + accessTokenKey);

      String refreshTokenKey = generateRandomKey((byte) 16);
      System.out.println("Refresh token key: " + refreshTokenKey);
   }

   private static String generateRandomKey(byte i) {
      byte[] bytes = new byte[i];
      new SecureRandom().nextBytes(bytes);
      String base64EncodedKey =  Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);

      Algorithm.HMAC256(base64EncodedKey);
      return "{\"kty\":\"oct\",\"k\":\"" + base64EncodedKey + "\"}";
   }
}
