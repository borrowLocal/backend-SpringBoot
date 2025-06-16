package com.example.borolo.repository;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.borolo.domain.Review;
import com.example.borolo.dto.response.ReviewResponseDto;


@Mapper
public interface ReviewDao {
    List<ReviewResponseDto> findByUserTargetId(@Param("user_target_id") int user_id);
    List<ReviewResponseDto> findByUserWriteId(@Param("user_write_id") int user_id);
    Review findByRentalId(@Param("rental_id") int rental_id);

    void insert(Review review);
}
