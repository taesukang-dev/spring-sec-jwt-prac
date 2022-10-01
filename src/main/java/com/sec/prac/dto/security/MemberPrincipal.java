package com.sec.prac.dto.security;

import com.sec.prac.domain.Member;
import com.sec.prac.domain.Role;
import lombok.Data;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberPrincipal implements UserDetails {
    private String username;
    private String password;
    Collection<? extends GrantedAuthority> authorities;
    private String nickname;
    private int age;

    public static MemberPrincipal of(String username, String password, String nickname, int age, Role role) {
        Set<Role> roleTypes = Set.of(role);

        return new MemberPrincipal(
                username,
                password,
                roleTypes.stream().map(Role::getName).map(SimpleGrantedAuthority::new).collect(Collectors.toUnmodifiableSet()),
                nickname,
                age
        );
    }

    public static MemberPrincipal from(Member member) {
        return MemberPrincipal.of(
                member.getEmail(),
                member.getPassword(),
                member.getNickname(),
                member.getAge(),
                member.getRole()
        );
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return username; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() {return true; }
}