package com.timetracking;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class User {

   private String id;

   private String name;

   private Double rating;

   private Map<String, Double> estimates;

   private Boolean isBlocked;
}
