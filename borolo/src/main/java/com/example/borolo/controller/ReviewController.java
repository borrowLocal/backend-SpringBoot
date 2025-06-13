package com.example.borolo.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.borolo.dto.request.ReviewRequestDto;
import com.example.borolo.dto.response.ReviewResponseDto;
import com.example.borolo.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/reviews")
@Tag(name = "리뷰관리", description = "리뷰 관련 API")	
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 1. 리뷰 작성
    @PostMapping
    @Operation(summary = "리뷰 작성")
    public ResponseEntity<Void> writeReview(@RequestParam int user_id, @RequestBody @Valid ReviewRequestDto dto) {
        try {
            reviewService.writeReview(dto, user_id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 2. 받은 리뷰
    @GetMapping("/received/{user_id}")
    @Operation(summary = "받은 리뷰")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsReceived(@PathVariable int user_id) {
        return ResponseEntity.ok(reviewService.getReviewsReceived(user_id));
    }

    // 3. 쓴 리뷰
    @GetMapping("/written/{user_id}")
    @Operation(summary = "쓴 리뷰")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsWritten(@PathVariable int user_id) {
        return ResponseEntity.ok(reviewService.getReviewsWritten(user_id));
    }
}