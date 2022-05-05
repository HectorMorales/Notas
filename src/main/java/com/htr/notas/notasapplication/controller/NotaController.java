package com.htr.notas.notasapplication.controller;

import com.htr.notas.notasapplication.domain.Nota;
import com.htr.notas.notasapplication.service.INotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class NotaController {

    @Autowired
    private INotasService notaService;

    @GetMapping("/nota")
    public List<Nota> listarTodo(){
        return notaService.listarPorFecha();
    }

    @GetMapping("/nota/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") String id){
        Nota nota = notaService.listarId(Long.parseLong(id));
        if(nota.getId() == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "No existe el id de nota");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(nota);
    }

    @PostMapping("/nota")
    public ResponseEntity<?> agregar(@RequestBody Nota nota){
        if(nota.getNota().length() > 800){
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "La nota excede los 800 caracteres");
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        notaService.agregar(nota);
        return ResponseEntity.ok(nota);
    }

    @PutMapping("/nota")
    public ResponseEntity<?> modificar(@RequestBody Nota nota){
        Map<String, Object> response = new HashMap<>();
        if(nota.getNota().length() > 800){
            response.put("mensaje", "La nota excede los 800 caracteres");
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            notaService.modificar(nota);
        } catch (EntityNotFoundException e){
            response.put("mensaje","No existe el id de nota");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(nota);

    }

    @DeleteMapping("/nota")
    public void actualizar(@RequestBody Nota nota){
        notaService.eliminar(nota);
    }

}
