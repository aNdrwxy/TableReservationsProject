package com.tablereservations.project.demo_table_reservations.service;

import com.tablereservations.project.demo_table_reservations.dto.UserSecurityDto;
import com.tablereservations.project.demo_table_reservations.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsImpService implements UserDetailsService {
    private final UsuarioService usuarioService;

    public UserDetailsImpService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getUserByCorreo(correo);

        return buildUser(usuario);
    }

    private UserSecurityDto buildUser(Usuario usuario) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre()));

        return new UserSecurityDto(
                usuario.getCorreo(),
                usuario.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
    }
}
