package com.tablereservations.project.demo_table_reservations.controller;

import com.tablereservations.project.demo_table_reservations.dto.RegisterUsuarioDto;
import com.tablereservations.project.demo_table_reservations.service.RolService;
import com.tablereservations.project.demo_table_reservations.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolService rolService;

    public UsuarioController(UsuarioService usuarioService, RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    // localhos:8080/user
    @GetMapping
    public String list(Model model) {
        model.addAttribute("usuariolist",
                usuarioService.getAllUsuarios());
        return "user/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("usuario", new RegisterUsuarioDto());
        model.addAttribute("listrol",
                rolService.getAllRoles());
        return "user/create";
    }

    @PostMapping("/create")
    public String create(Model model,
                         @ModelAttribute("product") RegisterUsuarioDto usuarioDto) {
        usuarioService.createUsuario(usuarioDto);
        return "redirect:/user";
    }

    // localhost:8080/user/edit/1
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id) {

        model.addAttribute("usuario",
                usuarioService.getUsuarioDtoById(id));
        model.addAttribute("listcategory",
                rolService.getAllRoles());
        return "user/edit";
    }

    @PostMapping("/edit")
    public String edit(Model model,
                       @ModelAttribute("product") RegisterUsuarioDto usuarioDto) {
        usuarioService.editUsuario(usuarioDto);
        return "redirect:/user";
    }
}

