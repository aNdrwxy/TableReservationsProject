package com.tablereservations.project.demo_table_reservations.service;

import jakarta.mail.MessagingException;
import com.tablereservations.project.demo_table_reservations.dto.RegisterUsuarioDto;
import com.tablereservations.project.demo_table_reservations.model.Rol;
import com.tablereservations.project.demo_table_reservations.model.Usuario;
import com.tablereservations.project.demo_table_reservations.repository.RolRepository;
import com.tablereservations.project.demo_table_reservations.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.tablereservations.project.demo_table_reservations.util.RandomPassword;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tablereservations.project.demo_table_reservations.util.RandomPassword.generateRandomPassword;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final EmailService emailService;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.emailService = emailService;
    }

    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int idusuario) {
        return usuarioRepository.findById(idusuario).orElse(null);
    }

    public Usuario getUserByCorreo(String correo){
        return usuarioRepository.findByCorreo(correo);
    }

    public RegisterUsuarioDto getRegisterUserDto(int idusuario){
        Usuario usuario = usuarioRepository.findById(idusuario).orElse(null);
        if(usuario != null){
            RegisterUsuarioDto registerUserDto = new RegisterUsuarioDto();
            registerUserDto.setIdusuario(usuario.getIdusuario());
            registerUserDto.setNombre(usuario.getNombre());
            registerUserDto.setApellidos(usuario.getApellidos());
            registerUserDto.setEdad(usuario.getEdad());
            registerUserDto.setCorreo(usuario.getCorreo());
            registerUserDto.setPassword(usuario.getPassword());
            registerUserDto.setActivo(usuario.getActivo());
            registerUserDto.setId_rol(usuario.getRol().getIdrol());
            return registerUserDto;
        }
        return null;
    }

    public void registerUser(RegisterUsuarioDto registerUserDto) throws MessagingException {
        Usuario usuario;
        String password;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (registerUserDto.getIdusuario() != null) {
            usuario = usuarioRepository.findById(registerUserDto.getIdusuario()).orElse(null);
            if (usuario == null) {
                throw new IllegalArgumentException("Usuario no encontrado");
            }
            usuario.setActivo(registerUserDto.isActivo());
        } else {
            usuario = new Usuario();

            password = generateRandomPassword(registerUserDto.getApellidos());
            usuario.setPassword(passwordEncoder.encode(password));
            usuario.setActivo(true);

            emailService.enviarEmail(registerUserDto.getCorreo(),
                    "Hola, " + registerUserDto.getNombre() +
                            ", tu clave de acceso es: " + password);
        }


        usuario.setNombre(registerUserDto.getNombre());
        usuario.setApellidos(registerUserDto.getApellidos());
        usuario.setCorreo(registerUserDto.getCorreo());
        usuario.setEdad(registerUserDto.getEdad());

        usuarioRepository.save(usuario);
    }

}
