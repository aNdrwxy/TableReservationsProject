package com.tablereservations.project.demo_table_reservations.repository;

import com.tablereservations.project.demo_table_reservations.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {
}
