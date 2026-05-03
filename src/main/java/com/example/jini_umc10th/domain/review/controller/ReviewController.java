package com.example.jini_umc10th.domain.review.controller;

import com.example.jini_umc10th.domain.review.dto.ReviewReqDTO;
import com.example.jini_umc10th.domain.review.dto.ReviewResDTO;
import com.example.jini_umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.jini_umc10th.domain.review.service.ReviewService;
import com.example.jini_umc10th.global.apiPayload.ApiResponse;
import com.example.jini_umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/api/stores/{store-id}/reviews")
    public ApiResponse<ReviewResDTO.postReviewResDTO> postReview(
            @PathVariable("store-id") Long storeId,
            @RequestBody ReviewReqDTO.postReviewReqDTO dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.postReview(storeId, dto));
    }
}