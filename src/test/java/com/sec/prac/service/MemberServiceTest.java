package com.sec.prac.service;

import com.sec.prac.domain.Member;
import com.sec.prac.domain.Role;
import com.sec.prac.dto.request.MemberSignUpRequest;
import com.sec.prac.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("tdd")
    @Test
    void tdd() {
        // given
//        MemberSignUpRequest memberRequest = MemberSignUpRequest.builder()
//                .email("asd@email.com")
//                .nickname("nick")
//                .age(22)
//                .password("12345678@")
//                .checkedPassword("12345678@")
//                .build();
        MemberSignUpRequest memberRequest = new MemberSignUpRequest("asd@email.com", "nick", 22, "12345678@@", "12345678@@", null);
        // when
        Long id = memberService.signUp(memberRequest);
        Member member = memberRepository.findById(id).get();
        // then
        Assertions.assertThat(member.getRole()).isEqualTo(Role.USER);

    }

}