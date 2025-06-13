package com.example.borolo.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.borolo.domain.Rental;
import com.example.borolo.dto.response.RentalApplicationListResponseDto;
import com.example.borolo.dto.response.RentalPaymentResponseDto;
import com.example.borolo.dto.response.RentalStatusResponseDto;

@Mapper
public interface RentalDao {

    List<RentalStatusResponseDto> findByUserId(@Param("user_id") int user_id); // 내 대여 내역 조회
    List<RentalApplicationListResponseDto> findByItemId(int item_id); // 대여 요청 목록 조회
    RentalPaymentResponseDto findPaymentInfoByRentalId(int rental_id); //결제 정보 조회
    //rental_id로 조회하면 항상 1개의 대여 건 ->  1개가 보장 되므로 List 사용 안함

    void insert(Rental rental); // 대여 신청
    void approveRental(int rental_id); // 대여 수락
    void rejectRental(int rental_id); // 대여 거절
    void completeRental(int rental_id); // 대여 상태 처리 (거래 완료) 
    
    Rental findById(int rental_id); 
    int countByUserId(int user_id);
    void updateStatus(@Param("rental_id") int rental_id, @Param("rental_status") String status); 
    
}
