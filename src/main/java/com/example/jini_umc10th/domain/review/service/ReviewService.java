package com.example.jini_umc10th.domain.review.service;

import com.example.jini_umc10th.domain.member.entity.Member;
import com.example.jini_umc10th.domain.member.exception.MemberException;
import com.example.jini_umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.jini_umc10th.domain.member.repository.MemberRepository;
import com.example.jini_umc10th.domain.mission.entity.Restaurant;
import com.example.jini_umc10th.domain.mission.exception.MissionException;
import com.example.jini_umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.jini_umc10th.domain.mission.repository.RestaurantRepository;
import com.example.jini_umc10th.domain.review.converter.ReviewConverter;
import com.example.jini_umc10th.domain.review.dto.ReviewReqDTO;
import com.example.jini_umc10th.domain.review.dto.ReviewResDTO;
import com.example.jini_umc10th.domain.review.entity.Review;
import com.example.jini_umc10th.domain.review.entity.ReviewPhoto;
import com.example.jini_umc10th.domain.review.repository.ReviewPhotoRepository;
import com.example.jini_umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReviewResDTO.postReviewResDTO postReview(long storeId, ReviewReqDTO.postReviewReqDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(storeId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.RESTAURANT_NOT_FOUND));

        Member member = memberRepository.findById(1L) // 임시 - 추후 인증에서 가져오기
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Review review = Review.builder()
                .rating(BigDecimal.valueOf(dto.rating()))
                .content(dto.content())
                .restaurant(restaurant)
                .member(member)
                .build();

        Review saved = reviewRepository.save(review);

        if (dto.imageUrl() != null) {
            Arrays.stream(dto.imageUrl()) // dto.imageUrl은 배열. 이걸 스트림으로 변환해서 하나씩 처리.
                    .map(url -> ReviewPhoto.builder() // 각 url을 reviewPhoto 객체로 변환.
                            .url(url)
                            .review(saved)
                            .build())
                    .forEach(reviewPhotoRepository::save); // 변환된 객체들을 하나씩 db에 저장.
        }

        return ReviewConverter.toPostReviewResDTO(saved.getId(), member.getName(), dto.rating(), dto.content(), dto.imageUrl(), saved.getCreatedAt());
    }
}
