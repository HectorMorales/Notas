package com.htr.notas.notasapplication.service;

import com.htr.notas.notasapplication.domain.Nota;
import com.htr.notas.notasapplication.repository.INotaRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class NotaService implements INotasService
{
    @Autowired
    private INotaRepository notaRepository;


    @Transactional
    @Override
    public void agregar(Nota nota) {
        nota.setFecha(DateTime.now().toDate());
        notaRepository.save(nota);
    }

    @Transactional
    @Override
    public void modificar(Nota nota) {
        Date fecha = notaRepository.getById(nota.getId()).getFecha();
        nota.setFecha(fecha);
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
        return notaRepository.findAll();
    }

    @ReadOnlyProperty
    @Override
    public List<Nota> listarPorFecha() {
       return notaRepository.findByOrderByFechaAsc();
    }

    @ReadOnlyProperty
    @Override
    public Nota listarId(Long id) {
        return notaRepository.findById(id).orElse(new Nota());
    }
}
