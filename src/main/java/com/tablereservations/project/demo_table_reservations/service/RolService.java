package com.tablereservations.project.demo_table_reservations.service;

import com.tablereservations.project.demo_table_reservations.model.Rol;
import com.tablereservations.project.demo_table_reservations.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }
}
