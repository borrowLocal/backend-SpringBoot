package com.example.borolo.service;


import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.borolo.dto.response.ItemListResponseDto;
import com.example.borolo.repository.FavoriteDao;
import com.example.borolo.repository.ItemDao;



@Service
public class FavoriteService {

    private final FavoriteDao favoriteDao;
    private final ItemDao itemDao;

    public FavoriteService(FavoriteDao favoriteDao, ItemDao itemDao) {
        this.favoriteDao = favoriteDao;
        this.itemDao = itemDao;
    }

    // 즐겨찾기 등록
    public void addFavorite(int user_id, int item_id) {
        if (favoriteDao.exists(user_id, item_id)) {
            throw new IllegalArgumentException("이미 즐겨찾기에 추가된 항목입니다.");
        }
        favoriteDao.insertFavorite(user_id, item_id);
    }

    // 즐겨찾기 삭제
    public void removeFavorite(int item_id, int user_id) {
        if (!favoriteDao.exists(user_id, item_id)) {
            throw new IllegalArgumentException("즐겨찾기에 등록되어 있지 않은 항목입니다.");
        }
        favoriteDao.deleteFavorite(user_id, item_id);
    }

    // 즐겨찾기 목록 조회
    public List<ItemListResponseDto> getFavoriteItems(int user_id) {
        // 즐겨찾기된 item_id 목록 조회
        List<Integer> favoriteItemIds = favoriteDao.findFavoriteItemIdsByUserId(user_id);

        if (favoriteItemIds == null || favoriteItemIds.isEmpty()) {
            return Collections.emptyList();
        }

        // 해당 item_id들로 item 리스트 가져오기 (재사용)
        return itemDao.findItemsByIds(favoriteItemIds);
    }
}
