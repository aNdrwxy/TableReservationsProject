package com.tablereservations.project.demo_table_reservations.repository;

import com.tablereservations.project.demo_table_reservations.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository  extends JpaRepository<Rol, Integer> {
}
