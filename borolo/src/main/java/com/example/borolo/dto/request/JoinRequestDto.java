package com.example.borolo.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class JoinRequestDto {
	
    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    
    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Size(min = 4, max = 8, message = "비밀번호는 4~8글자여야 합니다.")
    private String password;
    
    @NotBlank(message = "이름은 필수 입력입니다.")
    @Size(min = 2, max = 6, message = "이름은 2~6글자여야 합니다.")
    private String real_name;
    
    @NotBlank(message = "닉네임은 필수 입력입니다.")
    @Size(min = 2, max = 6, message = "닉네임은 2~6글자여야 합니다.")
    private String nick_name;
    
    @NotNull(message = "생년월일은 필수 입력입니다.")
    @Past(message = "생년월일은 과거 날짜여야 합니다.")
    private LocalDate birth_date;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public LocalDate getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(LocalDate birth_date) {
		this.birth_date = birth_date;
	}
} 