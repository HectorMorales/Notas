package com.htr.notas.notasapplication.service;

import com.htr.notas.notasapplication.domain.Nota;
import com.htr.notas.notasapplication.repository.INotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class NotaService implements INotasService
{
    @Autowired
    private INotaRepository notaRepository;


    @Transactional
    @Override
    public void agregar(Nota nota) {
        notaRepository.save(nota);
    }

    @Transactional
    @Override
    public void modificar(Nota nota) {
        notaRepository.save(nota);
    }

    @Transactional
    @Override
    public void eliminar(Nota nota) {
        notaRepository.delete(nota);
    }

    @ReadOnlyProperty
    @Override
    public List<Nota> listarTodo() {
        List<Nota> notas= notaRepository.findAll();
        return notas;
    }

    @ReadOnlyProperty
    @Override
    public Nota listarId(Long id) {
        Nota nota = notaRepository.findById(id).orElse(new Nota());
        return nota;
    }
}
