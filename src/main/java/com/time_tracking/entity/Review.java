package com.time_tracking.entity;

import com.time_tracking.dto.UserDto;
import com.time_tracking.utils.UuidGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Review {

   String id;

   String comment;

   Double estimates;

   UserDto author;

   public Review() {
      this.id = UuidGenerator.generate().toString();
   }
}
