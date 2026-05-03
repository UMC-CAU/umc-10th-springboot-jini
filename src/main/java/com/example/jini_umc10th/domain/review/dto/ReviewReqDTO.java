package com.example.jini_umc10th.domain.review.dto;

public class ReviewReqDTO {

    public record postReviewReqDTO(
            int rating,
            String content,
            String[] imageUrl
    ) {}
}
