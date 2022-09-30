package com.security.example.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import lombok.Data;
import lombok.Builder;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private boolean activated;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Authority> authorities = new HashSet<>();
}
