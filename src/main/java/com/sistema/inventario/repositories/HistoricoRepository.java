package com.sistema.inventario.repositories;

import com.sistema.inventario.entities.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    @Query("SELECT h FROM Historico h WHERE h.movimiento = :movimiento")
    List<Historico> findByMovimiento(@Param("movimiento") String movimiento);
}
