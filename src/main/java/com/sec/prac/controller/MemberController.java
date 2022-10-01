package com.sec.prac.controller;

import com.sec.prac.dto.request.MemberSignInRequest;
import com.sec.prac.dto.request.MemberSignUpRequest;
import com.sec.prac.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public Long join(@Valid @RequestBody MemberSignUpRequest memberSignUpRequest) {
        return memberService.signUp(memberSignUpRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody MemberSignInRequest memberSignInRequest) {
        return memberService.login(memberSignInRequest);
    }
}
