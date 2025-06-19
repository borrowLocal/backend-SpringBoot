package com.example.borolo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.borolo.dto.request.EmailRequestDto;
import com.example.borolo.dto.request.EmailVerificationRequestDto;
import com.example.borolo.service.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/email")
@Tag(name = "이메일발송", description = "이메일을 통한 인증코드 발송 API")
public class EmailController {
	@Autowired
    private EmailService emailService;

    @PostMapping("/send-code")
    @Operation(summary = "인증번호 이메일 전송")
    public ResponseEntity<?> sendVerificationCode(@RequestBody EmailRequestDto requestDto) {
        String email = requestDto.getEmail();
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("이메일을 입력해주세요.");
        }

        String verificationCode = emailService.createVerificationCode();

        try {
            emailService.sendVerificationCode(email, verificationCode);
            return ResponseEntity.ok("인증 코드가 이메일로 전송되었습니다.");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("이메일 전송 중 오류가 발생했습니다.");
        }
    }
    
    @PostMapping("/verify-code")
    @Operation(summary = "이메일 유효시간 검증용(단순 검증용)")
    public ResponseEntity<?> verifyCode(@RequestBody EmailVerificationRequestDto dto) {
        boolean isValid = emailService.verifyCode(dto.getEmail(), dto.getCode());

        if (isValid) {
            return ResponseEntity.ok("인증 성공");
        } else {
            return ResponseEntity.badRequest().body("인증 실패 또는 인증코드 만료됨.");
        }
    }

}