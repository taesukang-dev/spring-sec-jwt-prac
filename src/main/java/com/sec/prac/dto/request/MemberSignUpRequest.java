package com.sec.prac.dto.request;

import com.sec.prac.domain.Member;
import com.sec.prac.domain.Role;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignUpRequest {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, message = "닉네임이 너무 짧습니다.")
    private String nickname;

    @NotNull(message = "나이를 입력해주세요.")
    @Range(min = 0, max = 150)
    private int age;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = " ^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,30}$",
    message = "비밀번호는 8 ~ 30 이면서 특수문자를 포함해야 합니다.")
    private String password;

    private String checkedPassword;

    private Role role;

    public Member toEntity() {
        return Member.of(email, nickname, age, password, Role.USER);
    }
}
