package com.tablereservations.project.demo_table_reservations.repository;

import com.tablereservations.project.demo_table_reservations.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {

    Usuario findByCorreo(String correo);

    @Transactional
    @Modifying
    @Query(value = """
            update usuario set activo = :activo, nombre = :nombre, 
            apellidos = :apellidos where idusuario = :idusuario
            """, nativeQuery = true)
    void updateUser(@Param("activo") boolean activo,
                    @Param("nombre") String nombre,
                    @Param("apellidos") String apellidos,
                    @Param("idusuario") Integer idusuario);
}

