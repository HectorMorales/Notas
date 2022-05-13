package com.htr.notas.notasapplication.controller;

import com.htr.notas.notasapplication.dto.NotaDTO;
import com.htr.notas.notasapplication.service.INotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/nota")
public class NotaController {

    @Autowired
    private INotaService notaService;

    @GetMapping
    public List<NotaDTO> listarTodo(){
        return notaService.listarPorFecha();
    }

    @PostMapping
    public ResponseEntity<?> agregar(@RequestBody NotaDTO nota){
        if(nota.getNota().length() > 800){
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "La nota excede los 800 caracteres");
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        NotaDTO notaDTORespuesta = notaService.agregar(nota);
        return ResponseEntity.ok(notaDTORespuesta);
    }

    @PutMapping
    public ResponseEntity<?> modificar(@RequestBody NotaDTO notaDTO) {
        Map<String, Object> response = new HashMap<>();
        if (notaDTO.getNota().length() > 800) {
            response.put("mensaje", "La nota excede los 800 caracteres");
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        NotaDTO notaDTORespuesta = notaService.listarId(notaDTO.getId());
        if(notaDTORespuesta.getId() == null) {
            response.put("mensaje", "No existe el id de nota");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        notaDTORespuesta = notaService.modificar(notaDTO);
        return ResponseEntity.ok(notaDTORespuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") String id){
        Map<String, Object> response = new HashMap<>();
        long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            response.put("mensaje", "No es correcto el id de nota");
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        NotaDTO notaDTORespuesta = notaService.listarId(idLong);
        if(notaDTORespuesta.getId() == null) {
            response.put("mensaje", "No existe el id de nota");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(notaDTORespuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>eliminar(@PathVariable("id") String id){
        Map<String, Object> response = new HashMap<>();
        long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            response.put("mensaje", "No es correcto el id de nota");
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        NotaDTO notaDTO = notaService.listarId(idLong);
        if(notaDTO.getId() == null) {
            response.put("mensaje", "No existe el id de nota");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        notaService.eliminar(notaDTO);
        return ResponseEntity.ok(notaDTO);
    }


}
