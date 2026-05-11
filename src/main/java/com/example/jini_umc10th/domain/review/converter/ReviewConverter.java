package com.example.jini_umc10th.domain.review.converter;

import com.example.jini_umc10th.domain.review.dto.ReviewResDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static ReviewResDTO.postReviewResDTO toPostReviewResDTO(
            Long reviewId,
            String name,
            int rating,
            String content,
            String[] imageUrl,
            LocalDateTime createdAt
    ){
        return ReviewResDTO.postReviewResDTO.builder()
                .reviewId(reviewId) // 임시
                .name(name)
                .rating(rating)
                .content(content)
                .imageUrl(imageUrl)
                .createdAt(createdAt)
                .build();
    }
}
