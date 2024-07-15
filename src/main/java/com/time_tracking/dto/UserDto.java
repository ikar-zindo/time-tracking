package com.time_tracking.dto;

import com.time_tracking.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

   String id;

   String name;

   public UserDto(User user) {
      this.id = user.getId();
      this.name = user.getName();
   }
}
