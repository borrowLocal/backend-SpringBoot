package com.example.borolo.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.borolo.domain.Item;
import com.example.borolo.dto.response.ItemListResponseDto;
import com.example.borolo.dto.response.ItemSummaryDto;

@Mapper
public interface ItemDao {
    Item findById(int item_id); //물품 단일 조회
    List<Item> findByCategoryId(int category_id);
    //위치기반전체물품조회
    List<ItemListResponseDto> findByLocation(@Param("location") String location, @Param("category_id") Integer category_id);
    List<ItemSummaryDto> findItemSummaryByUserId(@Param("user_id") int user_id); // 등록 물품 조회
    List<ItemListResponseDto> findItemsByIds(@Param("itemIds") List<Integer> itemIds); //즐겨찾기된 아이템 list 조회
    
    void updateItemStatusToRequested(@Param("item_id") int item_id , @Param("status") String status); //대여신청자존재 -> item 상태 업뎃
    
    List<Item> findAvailableItems(); // 대여 가능 목록
    int countByUserId(int user_id);

    void insert(Item item);
    void update(Item item);
    int delete(int item_id);
}