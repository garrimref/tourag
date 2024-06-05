package com.reltour.tourag.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    MODERATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
