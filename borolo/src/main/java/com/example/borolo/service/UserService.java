package com.example.borolo.service;


import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.borolo.domain.User;
import com.example.borolo.dto.request.JoinRequestDto;
import com.example.borolo.dto.request.LoginRequestDto;
import com.example.borolo.dto.request.PasswordResetRequestDto;
import com.example.borolo.dto.request.PasswordverificationRequestDto;
import com.example.borolo.dto.request.UpdateUserProfileRequestDto;
import com.example.borolo.dto.response.UserProfileResponseDto;
import com.example.borolo.repository.UserDao;



@Service
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    // 1. 회원가입
    public void registerUser(JoinRequestDto dto) {
        if (userDao.findByEmail(dto.getEmail()) != null) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        if (userDao.findByNickName(dto.getNick_name()) != null) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        if (dto.getNick_name().equals(dto.getReal_name())) {
            throw new IllegalArgumentException("닉네임과 이름은 같을 수 없습니다.");
        }
        
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(encodedPassword); // 암호화된 비밀번호 저장
        user.setReal_name(dto.getReal_name());
        user.setNick_name(dto.getNick_name());
        user.setBirth_date(dto.getBirth_date());
        user.setCreate_time(LocalDateTime.now());
        user.setRating(0.0f);

        userDao.insert(user);
    }

    // 2. 로그인
    public User login(LoginRequestDto dto) {
        User user = userDao.findByEmail(dto.getEmail());

        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 계정입니다.");
        }

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }
    
    // 3. 비밀번호 재설정
    public void resetPassword(PasswordResetRequestDto dto) {
	    User user = userDao.findByEmail(dto.getEmail());
	    if (user == null) {
	        throw new IllegalArgumentException("가입된 사용자가 아닙니다.");
	    }
	    userDao.updatePassword(dto.getEmail(), dto.getNew_password());
    }

    // 5-1. 마이페이지 정보 조회
    public UserProfileResponseDto getUserProfile(int user_id) {
        User user = userDao.findById(user_id);
        if (user == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }

        UserProfileResponseDto dto = new UserProfileResponseDto();
        dto.setNick_name(user.getNick_name());
        dto.setRating(user.getRating());
        dto.setReal_name(user.getReal_name());
        dto.setBirth_date(user.getBirth_date());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());

        return dto;
    }


    // 5-2. 개인정보 수정
    public void updateUserProfile(UpdateUserProfileRequestDto dto) {
        User existing = userDao.findById(dto.getUser_id());
        if (existing == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }

        if (!existing.getNick_name().equals(dto.getNick_name())) {
            if (userDao.findByNickName(dto.getNick_name()) != null) {
                throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
            }
        }

        existing.setReal_name(dto.getReal_name());
        existing.setNick_name(dto.getNick_name());
        existing.setBirth_date(dto.getBirth_date());

        userDao.update(existing);
    }


    // 6. 비밀번호 확인
    public boolean verifyPassword(PasswordverificationRequestDto dto) {
        User user = userDao.findById(dto.getUser_id());

        if (user == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }

        return user.getPassword().equals(dto.getPassword());
    }

    // 7. 회원 탈퇴
    public void deleteUser(int user_id) {
        int deletedCount = userDao.deleteUser(user_id);  // 삭제된 행 수 반환
        
        if (deletedCount == 0) {
            throw new IllegalStateException("삭제할 수 없는 상태입니다. 대여중인 물품이 있거나 존재하지 않는 사용자입니다.");
        }
        
        userDao.deleteUser(user_id);
    }
}