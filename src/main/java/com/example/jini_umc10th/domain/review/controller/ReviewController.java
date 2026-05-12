package com.example.jini_umc10th.domain.review.controller;

import com.example.jini_umc10th.domain.review.dto.ReviewReqDTO;
import com.example.jini_umc10th.domain.review.dto.ReviewResDTO;
import com.example.jini_umc10th.domain.review.service.ReviewService;
import com.example.jini_umc10th.global.apiPayload.ApiResponse;
import com.example.jini_umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.jini_umc10th.global.apiPayload.code.GeneralSuccessCode;
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
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.postReview(storeId, dto));
    }

    @GetMapping("/api/members/me/reviews")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.reviewDTO>> getReviews(
            @RequestParam Long memberId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getReviews(memberId, pageSize, cursor, query));
    }
}