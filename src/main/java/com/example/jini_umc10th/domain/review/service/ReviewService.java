package com.example.jini_umc10th.domain.review.service;

import com.example.jini_umc10th.domain.review.converter.ReviewConverter;
import com.example.jini_umc10th.domain.review.dto.ReviewResDTO;
import com.example.jini_umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {

    // private final ReviewRepository reviewRepository;

    public ReviewResDTO.postReviewResDTO postReview(int rating, String content, String[] imageUrl){
        // TODO : 리뷰 DB 저장 구현
        return ReviewConverter.toPostReviewResDTO(1L, rating, content, imageUrl, LocalDateTime.now()); // 임시 값 넣어둠
    }
}
