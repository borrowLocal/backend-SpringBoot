package com.example.borolo.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.borolo.domain.Rental;

@Mapper
public interface RentalDao {
    Rental findById(int rental_id);
    List<Rental> findByUserId(int user_id);
    List<Rental> findByItemId(int item_id);
    int countByUserId(int user_id);
    
    void insert(Rental rental);
    void updateStatus(@Param("rental_id") int rental_id, @Param("status") String status);
    void approveRental(int rental_id);
    void completeRental(int rental_id);
}
