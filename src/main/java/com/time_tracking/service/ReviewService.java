package com.time_tracking.service;

import com.time_tracking.dto.UserDto;
import com.time_tracking.entity.Review;
import com.time_tracking.entity.User;
import com.time_tracking.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class ReviewService {

   private final UserService userService;

   // CREATE - REVIEW
   public Review addReview(String senderUserId, String recipientUserId, Review review)
         throws ExecutionException, InterruptedException {

      review.setAuthor(new UserDto(userService.getUserById(senderUserId)));
      User recipientUser = userService.getUserById(recipientUserId);
      recipientUser.getReceivedReviews().add(review);
      userService.updateUser(recipientUserId, recipientUser);

      updateUserRating(recipientUserId);
      return review;
   }

   // UPDATE - USER RATING
   private void updateUserRating(String userId)
         throws ExecutionException, InterruptedException {

      User user = userService.getUserById(userId);
      assert user != null;
      List<Review> reviews = user.getReceivedReviews();

      if (reviews == null) {
         throw new UserException("Estimates not found!");
      }

      Integer estimatesSize = reviews.size();
      Double estimatesSum = reviews.stream()
            .mapToDouble(Review::getEstimates)
            .sum();

      Double rating = estimatesSum / estimatesSize;
      user.setRating(rating);
      userService.updateUser(userId, user);
   }
}
