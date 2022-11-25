package com.ll.exam.jwtpractice.app.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.exam.jwtpractice.app.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
@Entity
public class Member extends BaseEntity {

    @Column(unique = true)
    private String username;

    @JsonIgnore
    private String password;
    private String email;

    public Member(long id) {
        super(id);
    }
    //현재 유저의 권한들을 리턴
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("MEMBER"));

        return authorities;
    }
}
