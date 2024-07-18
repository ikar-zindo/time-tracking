package com.time_tracking.controller;

import com.time_tracking.entity.Review;
import com.time_tracking.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

   private final ReviewService reviewService;

   @PostMapping
   public ResponseEntity<Review> addReview(@RequestParam String senderUserId,
                                           @RequestParam String recipientUserId,
                                           @RequestBody Review review) throws ExecutionException, InterruptedException {
      return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(reviewService.addReview(senderUserId, recipientUserId, review));
   }
}
