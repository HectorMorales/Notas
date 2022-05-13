package com.htr.notas.notasapplication.service;

import com.htr.notas.notasapplication.domain.Nota;
import com.htr.notas.notasapplication.dto.NotaDTO;

import java.util.List;

public interface INotaService {

    NotaDTO agregar(NotaDTO notaDTO);

    NotaDTO modificar(NotaDTO notaDTO);

    void eliminar(NotaDTO notaDTO);

    List<NotaDTO> listarTodo();

    List<NotaDTO> listarPorFecha();

    NotaDTO listarId(Long id);
}
