package com.example.jini_umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO{

    @Builder
    public record postReviewResDTO(
            Long reviewId,           // 생성된 리뷰 ID
            int rating,
            String content,
            String[] imageUrl,
            LocalDateTime createdAt  // 작성 시간
    ) {}
}
