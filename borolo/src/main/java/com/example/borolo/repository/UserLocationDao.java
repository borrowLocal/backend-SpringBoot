package com.example.borolo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.borolo.domain.UserLocation;

@Mapper
public interface UserLocationDao {
    UserLocation findByUserId(int user_id);
    
    void insert(UserLocation location);
    void update(UserLocation location);
}
