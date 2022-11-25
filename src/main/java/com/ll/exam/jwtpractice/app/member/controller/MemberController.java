package com.ll.exam.jwtpractice.app.member.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return "username : %s, password %s".formatted(loginDto.getUsername(), loginDto.getPassword());
    }

    @Data
    public static class LoginDto {
        private String username;
        private String password;
    }
}
