package com.htr.notas.notasapplication.service;

import com.htr.notas.notasapplication.domain.Nota;

import java.util.List;
import java.util.Optional;

public interface INotasService {

    void agregar(Nota nota);

    void modificar(Nota nota);

    void eliminar(Nota nota);

    List<Nota> listarTodo();

    Nota listarId(Long id);
}
