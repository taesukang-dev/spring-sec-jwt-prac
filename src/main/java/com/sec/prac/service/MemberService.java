package com.sec.prac.service;

import com.sec.prac.domain.Member;
import com.sec.prac.dto.request.MemberSignUpRequest;
import com.sec.prac.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(MemberSignUpRequest request) {
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }
        if (!request.getPassword().equals(request.getCheckedPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        Member member = memberRepository.save(request.toEntity());
        member.encodePassword(passwordEncoder);

        log.info("member role = {}", member.getRole());

        return member.getId();
    }

}