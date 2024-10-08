package com.EEIT85.bunnySugar.dto.users;

public class UsersSetPasswordDto {
    private String password;
    private String email;
    private String verifyingToken;

    public UsersSetPasswordDto() {
    }

    public UsersSetPasswordDto(String password, String email, String verifyingToken) {
        this.password = password;
        this.email = email;
        this.verifyingToken = verifyingToken;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifyingToken() {
        return verifyingToken;
    }

    public void setVerifyingToken(String verifyingToken) {
        this.verifyingToken = verifyingToken;
    }
}
