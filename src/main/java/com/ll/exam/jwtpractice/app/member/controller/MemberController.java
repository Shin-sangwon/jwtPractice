package com.ll.exam.jwtpractice.app.member.controller;

import com.ll.exam.jwtpractice.app.base.dto.RsData;
import com.ll.exam.jwtpractice.app.member.dto.LoginDto;
import com.ll.exam.jwtpractice.app.member.entity.Member;
import com.ll.exam.jwtpractice.app.member.service.MemberService;
import com.ll.exam.jwtpractice.app.security.entity.MemberContext;
import com.ll.exam.jwtpractice.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal MemberContext memberContext) {
        return "hello" + memberContext;
    }


    @PostMapping("/login")
    public ResponseEntity<RsData> login(@RequestBody LoginDto loginDto) {

        if(loginDto.isNotValid()) {
            return Util.Spring.responseEntityOf(RsData.of("F-1", "로그인 정보가 올바르지 않습니다."));
        }

        Member member = memberService.findByUsername(loginDto.getUsername()).orElse(null);

        if(member == null) {
            return Util.Spring.responseEntityOf(RsData.of("F-2", "일치하는 회원이 존재하지 않습니다."));
        }

        if(!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
            return Util.Spring.responseEntityOf(RsData.of("F-3", "비밀번호가 일치하지 않습니다."));
        }

        String accessToken = memberService.genAccessToken(member);

        return Util.Spring.responseEntityOf(
                RsData.of(
                        "S-1",
                        "로그인 성공, Access Token을 발급합니다.",
                        Util.mapOf(
                                "accessToken", accessToken
                        )
                ),
                Util.Spring.httpHeadersOf(
                        "Authentication", "JWT_Access_Token",
                              "TestHeaders", "TEST"));
    }

    @GetMapping("/me")
    public ResponseEntity<RsData> showMe(@AuthenticationPrincipal MemberContext member) {

        if(member == null) { // 임시 코드
            return Util.Spring.responseEntityOf(RsData.failOf(null));
        }

        return Util.Spring.responseEntityOf(RsData.successOf(member));
    }

}
