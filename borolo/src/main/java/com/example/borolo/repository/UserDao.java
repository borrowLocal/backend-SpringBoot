package com.example.borolo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.borolo.domain.User;

@Mapper
public interface UserDao {
    User findByEmail(String email);
    User findByNickName(String nick_name);
    User findById(int user_id);
    
    void insert(User user);
    void update(User user);
    void updatePassword(@Param("email") String email, @Param("password") String password);
}