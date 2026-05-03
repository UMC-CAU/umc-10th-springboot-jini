package com.example.jini_umc10th.domain.review.service;

import com.example.jini_umc10th.domain.review.converter.ReviewConverter;
import com.example.jini_umc10th.domain.review.dto.ReviewReqDTO;
import com.example.jini_umc10th.domain.review.dto.ReviewResDTO;
import com.example.jini_umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {

    // private final ReviewRepository reviewRepository;

    public ReviewResDTO.postReviewResDTO postReview(long storeId, ReviewReqDTO.postReviewReqDTO dto){
        // TODO : 리뷰 DB 저장 구현
        return ReviewConverter.toPostReviewResDTO(1L, dto.rating(), dto.content(), dto.imageUrl(), LocalDateTime.now()); // 임시 값 넣어둠
    }
}
