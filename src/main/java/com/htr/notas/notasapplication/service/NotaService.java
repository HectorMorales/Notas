package com.htr.notas.notasapplication.service;

import com.htr.notas.notasapplication.domain.Nota;
import com.htr.notas.notasapplication.dto.NotaDTO;
import com.htr.notas.notasapplication.repository.INotaRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaService implements INotaService
{
    @Autowired
    private INotaRepository notaRepository;


    private NotaDTO mapearDTO(Nota nota){
        NotaDTO notaDTO =  new NotaDTO();
        notaDTO.setId(nota.getId());
        notaDTO.setNota(nota.getNota());
        notaDTO.setFecha(nota.getFecha());
        return notaDTO;
    }

    private Nota mapearEntidad(NotaDTO notaDTO){
        Nota nota =  new Nota();
        nota.setId(notaDTO.getId());
        nota.setNota(notaDTO.getNota());
        nota.setFecha(notaDTO.getFecha());
        return nota;
    }

    @Transactional
    @Override
    public NotaDTO agregar(NotaDTO notaDTO){
        Nota nota = mapearEntidad(notaDTO);
        nota.setFecha(DateTime.now().toDate());
        Nota nuevaNota = notaRepository.save(nota);
        return mapearDTO(nuevaNota);
    }


    @Transactional
    @Override
    public NotaDTO modificar(NotaDTO notaDTO) {
        Nota nota = notaRepository.findById(notaDTO.getId()).orElse(new Nota());
        nota.setNota(notaDTO.getNota());
        notaRepository.save(nota);
        return mapearDTO(nota);
    }

    @Transactional
    @Override
    public void eliminar(NotaDTO notaDTO) {
        Nota nota = notaRepository.findById(notaDTO.getId()).orElse(new Nota());
        notaRepository.delete(nota);
    }

    @ReadOnlyProperty
    @Override
    public List<NotaDTO> listarTodo() {
        List<Nota> notas =  notaRepository.findAll();
        return notas.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @ReadOnlyProperty
    @Override
    public List<NotaDTO> listarPorFecha(){
        List<Nota> notas =  notaRepository.findByOrderByFechaAsc();
        return notas.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @ReadOnlyProperty
    @Override
    public NotaDTO listarId(Long id) {
        return mapearDTO(notaRepository.findById(id).orElse(new Nota()));
    }
}
