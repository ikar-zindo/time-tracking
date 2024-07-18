package com.time_tracking.entity;

import com.google.firebase.auth.UserRecord;
import com.time_tracking.utils.UuidGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Setter
@Getter
public class User {

   private String id;

   private String name;

   private String email;

   private String password;

   private Double rating;

   private List<Review> receivedReviews;

   private Boolean isBlocked;

   public User() throws ExecutionException, InterruptedException {
      this.id = UuidGenerator.generateUniqueId();
      this.rating = 0.0;
      this.receivedReviews = new ArrayList<>();
      this.isBlocked = false;
   }

   public static class Builder {

      private final User user = new User();

      public Builder() throws ExecutionException, InterruptedException {
      }

      public Builder id(String id) {
         user.id = id;
         return this;
      }

      public Builder name(String name) {
         user.name = name;
         return this;
      }

      public Builder rating(Double rating) {
         user.rating = rating;
         return this;
      }

      public Builder receivedReviews(List<Review> receivedReviews) {
         user.receivedReviews = receivedReviews;
         return this;
      }

      public Builder isBlocked(Boolean isBlocked) {
         user.isBlocked = isBlocked;
         return this;
      }

      public User build() {
         return user;
      }
   }

   public static Builder builder() throws ExecutionException, InterruptedException {
      return new Builder();
   }
}
