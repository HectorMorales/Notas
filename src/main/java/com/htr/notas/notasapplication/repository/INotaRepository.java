package com.htr.notas.notasapplication.repository;

import com.htr.notas.notasapplication.domain.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotaRepository extends JpaRepository<Nota, Long> {

    List<Nota> findByOrderByFechaAsc();
}
