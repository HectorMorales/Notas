package com.htr.notas.notasapplication.controller;

import com.htr.notas.notasapplication.domain.Nota;
import com.htr.notas.notasapplication.service.INotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/nota")
public class NotaController {

    @Autowired
    private INotaService notaService;

    @GetMapping
    public List<Nota> listarTodo(){
        return notaService.listarPorFecha();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> agregar(@RequestBody Nota nota){
        if(nota.getNota().length() > 800){
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "La nota excede los 800 caracteres");
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        notaService.agregar(nota);
        return ResponseEntity.ok(nota);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> modificar(@RequestBody Nota nota){
        Map<String, Object> response = new HashMap<>();
        if(nota.getNota().length() > 800){
            response.put("mensaje", "La nota excede los 800 caracteres");
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            notaService.modificar(nota);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            response.put("mensaje","No existe el id de nota");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(nota);

    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?>eliminar(@RequestBody Nota nota){
        Nota existNota = notaService.listarId(nota.getId());
        if(existNota.getId() == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "No existe el id de nota");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        notaService.eliminar(nota);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @GetMapping("/{id}")
    @ReadOnlyProperty
    public ResponseEntity<?> listarId(@PathVariable("id") String id){
        Map<String, Object> response = new HashMap<>();
        long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            response.put("mensaje", "No existe el id de nota");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Nota nota = notaService.listarId(idLong);
        if(nota.getId() == null) {
            response.put("mensaje", "No existe el id de nota");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(nota);
    }

}
