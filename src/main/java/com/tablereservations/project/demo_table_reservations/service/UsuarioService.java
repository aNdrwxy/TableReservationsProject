package com.tablereservations.project.demo_table_reservations.service;

import com.tablereservations.project.demo_table_reservations.dto.RegisterUsuarioDto;
import com.tablereservations.project.demo_table_reservations.model.Rol;
import com.tablereservations.project.demo_table_reservations.model.Usuario;
import com.tablereservations.project.demo_table_reservations.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public RegisterUsuarioDto getUsuarioDtoById(int id){
        Usuario usuario = getUsuarioById(id);
        if(usuario != null){
            RegisterUsuarioDto usuarioDto = new RegisterUsuarioDto();
            usuarioDto.setIdusuario(usuario.getIdusuario());
            usuarioDto.setNombre(usuario.getNombre());
            usuarioDto.setApellidos(usuario.getApellidos());
            usuarioDto.setEdad(usuario.getEdad());
            usuarioDto.setCorreo(usuario.getCorreo());
            usuarioDto.setClave(usuario.getClave());
            usuarioDto.setId_rol(usuario.getRol().getIdrol());
            return usuarioDto;
        }
        return null;
    }

    public void editUsuario(RegisterUsuarioDto usuarioDto){
        Usuario editUsuario = getUsuarioById(usuarioDto.getIdusuario());
        editUsuario.setNombre(usuarioDto.getNombre());
        editUsuario.setApellidos(usuarioDto.getApellidos());
        editUsuario.setEdad(usuarioDto.getEdad());
        editUsuario.setCorreo(usuarioDto.getCorreo());
        editUsuario.setClave(usuarioDto.getClave());
        Rol rol = new Rol();
        rol.setIdrol(usuarioDto.getId_rol());
        editUsuario.setRol(rol);
        registerUsuario(editUsuario);
    }

    public void createUsuario(RegisterUsuarioDto usuarioDto){
        Usuario newUsuario = new Usuario();
        newUsuario.setNombre(usuarioDto.getNombre());
        newUsuario.setApellidos(usuarioDto.getApellidos());
        newUsuario.setEdad(usuarioDto.getEdad());
        newUsuario.setCorreo(usuarioDto.getCorreo());
        newUsuario.setClave(usuarioDto.getClave());
        Rol rol = new Rol();
        rol.setIdrol(usuarioDto.getId_rol());
        registerUsuario(newUsuario);
    }

    public void registerUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}
