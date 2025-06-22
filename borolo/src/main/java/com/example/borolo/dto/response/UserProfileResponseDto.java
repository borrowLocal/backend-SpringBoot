package com.example.borolo.dto.response;

import java.time.LocalDate;

public class UserProfileResponseDto {
	private String nick_name;
   private Float rating;
   private String real_name;
   private String password;
   private LocalDate birth_date;
   private String email;

   public String getNick_name() {
      return nick_name;
   }
   public void setNick_name(String nick_name) {
      this.nick_name = nick_name;
   }
   public Float getRating() {
      return rating;
   }
   public void setRating(Float rating) {
      this.rating = rating;
   }

   public String getReal_name() {
      return real_name;
   }
   public void setReal_name(String real_name) {
      this.real_name = real_name;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public LocalDate getBirth_date() {
      return birth_date;
   }
   public void setBirth_date(LocalDate birth_date) {
      this.birth_date = birth_date;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }

}
