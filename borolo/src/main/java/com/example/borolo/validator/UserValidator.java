package com.example.borolo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.borolo.dto.request.JoinRequestDto;
import com.example.borolo.repository.UserDao;

@Component
public class UserValidator implements Validator {

    private final UserDao userDao;

    public UserValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return JoinRequestDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinRequestDto dto = (JoinRequestDto) target;

        // R001: 실명 2~6자
        if (dto.getReal_name() == null || dto.getReal_name().trim().isEmpty()) {
            errors.rejectValue("real_name", "required", "이름은 필수 입력입니다.");
        } else if (dto.getReal_name().length() < 2 || dto.getReal_name().length() > 6) {
            errors.rejectValue("real_name", "length", "이름은 2~6글자여야 합니다.");
        }

        // R002: 닉네임 중복 + 2~6자
        if (dto.getNick_name() == null || dto.getNick_name().trim().isEmpty()) {
            errors.rejectValue("nick_name", "required", "닉네임은 필수 입력입니다.");
        } else {
            if (dto.getNick_name().length() < 2 || dto.getNick_name().length() > 6) {
                errors.rejectValue("nick_name", "length", "닉네임은 2~6글자여야 합니다.");
            }
            if (userDao.findByNickName(dto.getNick_name()) != null) {
                errors.rejectValue("nick_name", "duplicate", "이미 사용 중인 닉네임입니다.");
            }
        }

        // R003: 이메일 형식 + 중복
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            errors.rejectValue("email", "required", "이메일은 필수 입력입니다.");
        } else {
            String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
            if (!dto.getEmail().matches(emailRegex)) {
                errors.rejectValue("email", "format", "이메일 형식이 올바르지 않습니다.");
            }
            if (userDao.findByEmail(dto.getEmail()) != null) {
                errors.rejectValue("email", "duplicate", "이미 가입된 이메일입니다.");
            }
        }

        // R004: 비밀번호 조건
        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            errors.rejectValue("password", "required", "비밀번호는 필수 입력입니다.");
        } else {
            String pwRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,20}$";
            if (!dto.getPassword().matches(pwRegex)) {
                errors.rejectValue("password", "invalid", "비밀번호는 8~20자이며, 영문자/숫자/특수기호를 포함해야 합니다.");
            }
        }

        // R005: 비밀번호 확인
        if (dto.getPassword() == null || !dto.getPassword().equals(dto.getPassword())) {
            errors.rejectValue("password_check", "mismatch", "비밀번호가 일치하지 않습니다.");
        }

        // R006: 생년월일 필수
        if (dto.getBirth_date() == null) {
            errors.rejectValue("birth_date", "required", "생년월일은 필수 입력입니다.");
        }
    }
}