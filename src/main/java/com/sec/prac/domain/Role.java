package com.sec.prac.domain;

import lombok.Getter;

public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    @Getter private final String name;

    Role(String name) {
        this.name = name;
    }
}
