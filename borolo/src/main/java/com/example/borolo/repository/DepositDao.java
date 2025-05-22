package com.example.borolo.repository;

	
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.borolo.dto.response.DepositResponseDto;

@Mapper
public interface DepositDao {
    List<DepositResponseDto> findByRenterUserId(int user_id);
    List<DepositResponseDto> findByItemOwnerId(int user_id);
    
}