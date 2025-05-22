package com.example.borolo.repository;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.borolo.domain.Review;


@Mapper
public interface ReviewDao {
    List<Review> findByUserTargetId(@Param("user_id") int user_id);
    List<Review> findByUserWriteId(@Param("user_id") int user_id);
    Review findByRentalId(@Param("rental_id") int rental_id);

    void insert(Review review);
}
