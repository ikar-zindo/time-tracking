package com.timetracking.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class User {

   private String id;

   private String name;

   private Double rating;

   private Map<String, Double> estimates;

   private Boolean isBlocked;

   public static class Builder {

      private User user = new User();

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

      public Builder estimates(Map<String, Double> estimates) {
         user.estimates = estimates;
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

   public static Builder builder() {
      return new Builder();
   }
}
