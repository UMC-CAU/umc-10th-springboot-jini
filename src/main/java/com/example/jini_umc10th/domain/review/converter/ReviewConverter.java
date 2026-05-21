package com.example.jini_umc10th.domain.review.converter;

import com.example.jini_umc10th.domain.review.dto.ReviewResDTO;
import com.example.jini_umc10th.domain.review.entity.Review;
import com.example.jini_umc10th.domain.review.entity.ReviewPhoto;
import com.example.jini_umc10th.domain.review.entity.ReviewReply;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewConverter {

    // 리뷰 작성
    public static ReviewResDTO.postReviewResDTO toPostReviewResDTO(
            Long reviewId,
            String name,
            BigDecimal rating,
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

    // 페이지네이션 틀 생성
    public static <T> ReviewResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }

    // 리뷰 조회
    public static ReviewResDTO.reviewDTO toReviewDTO(Review review){

        List<String> imageUrl = review.getReviewPhotos().stream()
                .map(ReviewPhoto::getUrl)
                .toList();

        List<ReviewResDTO.replyDTO> replyContents = review.getReviewReplies().stream()
                .map(reply -> ReviewResDTO.replyDTO.builder()
                        .content(reply.getContent())
                        .createdAt(reply.getCreatedAt())
                        .build())
                .toList();

        return ReviewResDTO.reviewDTO.builder()
                .memberName(review.getMember().getName())
                .restaurantName(review.getRestaurant().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .imageUrl(imageUrl)
                .createdAt(review.getCreatedAt())
                .replyContent(replyContents)
                .build();
    }
}
