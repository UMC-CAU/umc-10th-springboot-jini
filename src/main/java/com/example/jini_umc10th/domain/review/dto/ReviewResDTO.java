package com.example.jini_umc10th.domain.review.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO{

    @Builder
    public record postReviewResDTO(
            Long reviewId,           // 생성된 리뷰 ID
            String name,
            BigDecimal rating,
            String content,
            String[] imageUrl,
            LocalDateTime createdAt  // 작성 시간
    ) {}

    // 사장님 답글
    @Builder
    public record replyDTO(
            String content,
            LocalDateTime createdAt
    ){}

    // 리뷰 조회
    @Builder
    public record reviewDTO(
            String memberName,
            String restaurantName,
            BigDecimal rating,
            String content,
            List<String> imageUrl,
            LocalDateTime createdAt,
            List<replyDTO> replyContent
    ) {}

    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}
}
