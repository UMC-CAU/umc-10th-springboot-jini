package com.example.jini_umc10th.domain.review.dto;

import java.math.BigDecimal;

public class ReviewReqDTO {

    public record postReviewReqDTO(
            BigDecimal rating,
            String content,
            String[] imageUrl
    ) {}
}
