package com.example.borolo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.borolo.domain.User;
import com.example.borolo.dto.request.JoinRequestDto;
import com.example.borolo.dto.request.LoginRequestDto;
import com.example.borolo.dto.request.PasswordResetRequestDto;
import com.example.borolo.dto.request.PasswordverificationRequestDto;
import com.example.borolo.dto.request.UpdateUserProfileRequestDto;
import com.example.borolo.dto.response.UserProfileResponseDto;
import com.example.borolo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/users")
@Tag(name = "회원관리", description = "사용자 관련 API")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. 회원가입
    @PostMapping
    @Operation(summary = "회원가입")
    public ResponseEntity<String> registerUser(@RequestBody @Valid JoinRequestDto dto) {
        try {
            userService.registerUser(dto);
            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 2. 로그인
    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequestDto dto) {
        try {
            User user = userService.login(dto);
            return ResponseEntity.ok(user); 
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    
    // 3. 비밀번호 재설정
    @PostMapping("/password/reset")
    @Operation(summary = "비밀번호 재설정")
    public ResponseEntity<Void> resetPassword(@RequestBody PasswordResetRequestDto dto) {
        try {
            userService.resetPassword(dto);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 5-1. 마이페이지 조회
    @GetMapping("/{user_id}")
    @Operation(summary = "마이페이지(메뉴) 조회")
    public ResponseEntity<UserProfileResponseDto> getUserProfile(@PathVariable int user_id) {
        try {
            UserProfileResponseDto dto = userService.getUserProfile(user_id);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 5-2. 개인정보 수정
    @PutMapping("/{user_id}")
    @Operation(summary = "개인정보 수정")
    public ResponseEntity<Void> updateProfile(@PathVariable int user_id, @RequestBody @Valid UpdateUserProfileRequestDto dto) {
        try {
            if (dto.getUser_id() == null || !dto.getUser_id().equals(user_id)) {
                throw new IllegalArgumentException("잘못된 접근입니다.");
            }
            userService.updateUserProfile(dto);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 6. 비밀번호 확인
    @PostMapping("/verify-password")
    @Operation(summary = "비밀번호 확인")
    public ResponseEntity<Boolean> verifyPassword(@RequestBody PasswordverificationRequestDto dto) {
        try {
            boolean result = userService.verifyPassword(dto);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //. 회원 탈퇴
    @DeleteMapping("/{user_id}")
    @Operation(summary = "회원 탈퇴")
    public ResponseEntity<Void> deleteUser(@PathVariable int user_id) {
        try {
            userService.deleteUser(user_id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
