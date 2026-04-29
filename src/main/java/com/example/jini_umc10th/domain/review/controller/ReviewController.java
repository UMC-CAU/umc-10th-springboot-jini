package com.example.jini_umc10th.domain.review.controller;

import com.example.jini_umc10th.domain.review.dto.ReviewResDTO;
import com.example.jini_umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.jini_umc10th.domain.review.service.ReviewService;
import com.example.jini_umc10th.global.apiPayload.ApiResponse;
import com.example.jini_umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/api/stores/{store-id}/reviews")
    public ApiResponse<ReviewResDTO.postReviewResDTO> postReview(
            @RequestParam int rating,
            @RequestParam String content,
            @RequestParam String[] imageUrl
    ) {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.postReview(rating, content, imageUrl));
    }
}
