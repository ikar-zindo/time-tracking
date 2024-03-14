package com.timetracking.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.timetracking.TimeTrackingApplication;
import com.timetracking.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

   @Mock
   private UserService userServiceMock;

   @InjectMocks
   private UserService userServiceTest;

   private User expectedUser;

   @BeforeEach
   void init() {
      expectedUser = User.builder()
              .id("2097b5ce-7d73-4be5-9465-1732e7ce3de9")
              .name("TestName")
              .rating(0.0)
              .estimates(new HashMap<>())
              .isBlocked(false)
              .build();
   }

   @Test
   void createUserTest() throws ExecutionException, InterruptedException {


   }

}