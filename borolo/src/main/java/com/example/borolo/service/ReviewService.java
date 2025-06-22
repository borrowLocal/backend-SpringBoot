package com.example.borolo.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.borolo.domain.Review;
import com.example.borolo.dto.request.ReviewRequestDto;
import com.example.borolo.dto.response.ReviewReportTargetDto;
import com.example.borolo.dto.response.ReviewResponseDto;
import com.example.borolo.repository.RentalDao;
import com.example.borolo.repository.ReviewDao;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ReviewService {
    private final ReviewDao reviewDao;
    private final RentalDao rentalDao;
    
    public ReviewService(ReviewDao reviewDao,RentalDao rentalDao) {
        this.reviewDao = reviewDao;
        this.rentalDao = rentalDao;
    }

    // 1. 리뷰 작성
    public void writeReview(ReviewRequestDto dto, int writer_id) {
    	
        //자기 자신 평가 금지 검사
        if (writer_id == dto.getUser_target_id()) {
            throw new IllegalArgumentException("자기 자신에게 리뷰를 작성할 수 없습니다.");
        }
        
        Review review = new Review();
        review.setContent(dto.getContent());
        review.setRating(dto.getRating());
        review.setRental_id(dto.getRental_id());
        review.setUser_write_id(writer_id);
        review.setUser_target_id(dto.getUser_target_id());

        reviewDao.insert(review);
    }

    // 2. 받은 리뷰 목록
    public List<ReviewResponseDto> getReviewsReceived(int user_id) {
        return reviewDao.findByUserTargetId(user_id);
    }

    // 3. 작성한 리뷰 목록
    public List<ReviewResponseDto> getReviewsWritten(int user_id) {
        return reviewDao.findByUserWriteId(user_id);
    }
    
    //4. 제공자->대여자 리뷰모달 조회
    public ReviewReportTargetDto getReviewTargetFromItem(int itemId) {
        return rentalDao.findUserInfoByItemId(itemId)
                .orElseThrow(() -> new EntityNotFoundException("해당 item의 대여 정보가 없습니다."));
    }

    //5. 대여자->제공자 리뷰모달 조회
    public ReviewReportTargetDto getReviewTargetFromRental(int rentalId) {
        return rentalDao.findUserInfoByRentalId(rentalId)
                .orElseThrow(() -> new EntityNotFoundException("해당 rental의 정보가 없습니다."));
    }
}
