package com.EEIT85.bunnySugar.dto;

import java.time.LocalDateTime;

public class MemberUpdateDto {

    private String name;
    private String gender;
    private String email;
    private String phone;
    private LocalDateTime birthday;

    public MemberUpdateDto() {

    }

    public MemberUpdateDto(String name, String gender, String email, String phone) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.birthday = LocalDateTime.now();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }
}
