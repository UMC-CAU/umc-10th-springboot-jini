package com.example.jini_umc10th.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ReviewReqDTO {

    public record postReviewReqDTO(
            @NotNull(message = "별점은 필수입니다.")
            BigDecimal rating,
            @NotBlank(message = "내용은 필수입니다.")
            String content,
            String[] imageUrl
    ) {}
}
