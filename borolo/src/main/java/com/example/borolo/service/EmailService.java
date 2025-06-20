package com.example.borolo.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private static final long EXPIRE_TIME_MS = 3 * 60 * 1000; 

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;
    
    private Map<String, VerificationCodeInfo> codeStorage = new ConcurrentHashMap<>();

    // 인증번호 생성
    public String createVerificationCode() {
        int code = 100000 + new Random().nextInt(900000);
        return String.valueOf(code);
    }

    // 이메일 전송 및 코드 저장
    public void sendVerificationCode(String toEmail, String code) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

        helper.setTo(toEmail);
        try {
            helper.setFrom(fromEmail, "바로로");
        } catch (UnsupportedEncodingException e) {
            throw new MessagingException("Invalid from address", e);
        }
        helper.setSubject("바로로 인증 코드 안내");
        helper.setText("귀하의 인증 코드는 " + code + "입니다.\n\n3분 이내에 인증을 완료해주세요.");

        mailSender.send(message);

        codeStorage.put(toEmail, new VerificationCodeInfo(code, System.currentTimeMillis()));
    }

    // 인증코드 검증
    public boolean verifyCode(String email, String inputCode) {
        VerificationCodeInfo info = codeStorage.get(email);
        if (info == null) {
            System.out.println("[인증 실패] 저장된 정보 없음: email=" + email);
            return false;
        }

        // 만료시간 체크
        long elapsed = System.currentTimeMillis() - info.getCreatedAt();
        if (elapsed > EXPIRE_TIME_MS) {
            System.out.println("[인증 실패] 코드 만료됨: email=" + email + ", 경과시간(ms)=" + elapsed);
            codeStorage.remove(email);
            return false;
        }

        // 디버깅 로그: 저장된 코드 vs 입력된 코드
        System.out.println("[인증 시도] email=" + email);
        System.out.println(" ├─ 저장된 코드: " + info.getCode());
        System.out.println(" └─ 입력된 코드: " + inputCode);

        boolean matched = info.getCode().equals(inputCode);
        if (matched) {
            System.out.println("[인증 성공]");
            codeStorage.remove(email); 
        } else {
            System.out.println("[인증 실패] 코드 불일치");
        }
        return matched;
    }


    // 인증코드+생성시간 저장용 내부 클래스
    private static class VerificationCodeInfo {
        private final String code;
        private final long createdAt;

        public VerificationCodeInfo(String code, long createdAt) {
            this.code = code;
            this.createdAt = createdAt;
        }
        public String getCode() { return code; }
        public long getCreatedAt() { return createdAt; }
    }
}
