package com.sec.prac.domain;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Member {
    @Column(name = "member_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    private String email; // login id

    @Column(length = 50)
    private String nickname;

    private int age;

    @Column(length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(String email, String nickname, int age, String password, Role role) {
        this.email = email;
        this.nickname = nickname;
        this.age = age;
        this.password = password;
        this.role = role;
    }

    public static Member of(String email, String nickname, int age, String password, Role role) {
        return new Member(email, nickname, age, password, role);
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
