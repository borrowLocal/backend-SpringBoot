package com.example.borolo.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.borolo.domain.Item;

@Mapper
public interface FavoriteDao {
    List<Item> findFavoritesByUserId(@Param("user_id") int user_id);
    int countByUserId(int user_id);

    void insertFavorite(@Param("user_id") int user_id, @Param("item_id") int item_id);
    void deleteFavorite(@Param("user_id") int user_id, @Param("item_id") int item_id);
    boolean exists(@Param("user_id") int user_id, @Param("item_id") int item_id);
}
