package com.ll.exam.jwtpractice.app.member.service;

import com.ll.exam.jwtpractice.app.member.entity.Member;
import com.ll.exam.jwtpractice.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(String username, String password, String email) {
        Member member = Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();

        memberRepository.save(member);

        return member;
    }
}
