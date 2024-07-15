package com.time_tracking.service;

import com.time_tracking.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

   @Mock
   private UserService userServiceMock;

   @InjectMocks
   private UserService userServiceTest;

   private User expectedUser;

   @BeforeEach
   void init() throws ExecutionException, InterruptedException {
      expectedUser = User.builder()
              .id("2097b5ce-7d73-4be5-9465-1732e7ce3de9")
              .name("TestName")
              .rating(0.0)
              .receivedReviews(new LinkedList<>())
              .isBlocked(false)
              .build();
   }

   @Test
   void createUserTest() throws ExecutionException, InterruptedException {


   }

}