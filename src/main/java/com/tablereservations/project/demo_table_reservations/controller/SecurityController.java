package com.tablereservations.project.demo_table_reservations.controller;

import com.tablereservations.project.demo_table_reservations.dto.RegisterUsuarioDto;
import com.tablereservations.project.demo_table_reservations.service.RolService;
import com.tablereservations.project.demo_table_reservations.service.UsuarioService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/security")
public class SecurityController {

    private final UsuarioService usuarioService;
    private final RolService rolService;

    public SecurityController(UsuarioService usuarioService, RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    @GetMapping("/user")
    public String index(Model model) {
        model.addAttribute("userList", usuarioService.getAllUsers());
        return "security/list";
    }

    @GetMapping("/user/create")
    public String create(Model model) {
        model.addAttribute("user", new RegisterUsuarioDto());
        model.addAttribute("rolList", rolService.getAllRoles());
        return "security/user-create";
    }

    @GetMapping("/user/edit/{id}")
    public String edit(Model model,
                       @PathVariable int id) {
        model.addAttribute("user", usuarioService.getRegisterUserDto(id));
        model.addAttribute("rolList", rolService.getAllRoles());
        return "security/user-edit";
    }

    @PostMapping("/user/register")
    public String register(@ModelAttribute("user") RegisterUsuarioDto dto) throws MessagingException {
        usuarioService.registerUser(dto);
        return "redirect:/security/user";
    }
}
