package com.timetracking;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.common.collect.ImmutableList;
import com.timetracking.domain.User;
import com.timetracking.exception.UserException;
import com.timetracking.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

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
   void getAllUsersTest() throws UserException, ExecutionException, InterruptedException {
      Firestore dbFirestore = mock(Firestore.class);
      CollectionReference usersCollection = mock(CollectionReference.class);

      QuerySnapshot querySnapshot = mock(QuerySnapshot.class);

      List<QueryDocumentSnapshot> queryDocumentSnapshots = ImmutableList.of(
              mock(QueryDocumentSnapshot.class),
              mock(QueryDocumentSnapshot.class)
      );

      ApiFuture<QuerySnapshot> apiFuture = mock(ApiFuture.class);

      when(dbFirestore.collection("users")).thenReturn(usersCollection);
      when(usersCollection.get()).thenReturn(apiFuture);
      when(apiFuture.get()).thenReturn(querySnapshot);
      when(querySnapshot.getDocuments()).thenReturn(queryDocumentSnapshots);


      when(queryDocumentSnapshots.get(0).toObject(User.class)).thenReturn(expectedUser);
      when(queryDocumentSnapshots.get(1).toObject(User.class)).thenReturn(expectedUser);

      List<User> actualUsers = userServiceMock.getAllUsers();
      actualUsers.add(expectedUser);

      assertIterableEquals(Arrays.asList(expectedUser), actualUsers);
   }
}
