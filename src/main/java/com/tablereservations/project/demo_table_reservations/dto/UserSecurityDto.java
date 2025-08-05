package com.tablereservations.project.demo_table_reservations.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserSecurityDto extends User {

    private String email;

    public UserSecurityDto(String correo,
                           String password,
                           boolean enabled,
                           boolean accountNonExpired,
                           boolean credentialsNonExpired,
                           boolean accountNonLocked,
                           Collection<? extends GrantedAuthority> authorities) {
        super(correo, password, enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked, authorities);

        this.email = correo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


