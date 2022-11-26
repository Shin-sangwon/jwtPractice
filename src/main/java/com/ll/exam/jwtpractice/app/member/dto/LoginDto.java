package com.ll.exam.jwtpractice.app.member.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;

    public boolean isNotValid() {
        return username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0;
    }
}
