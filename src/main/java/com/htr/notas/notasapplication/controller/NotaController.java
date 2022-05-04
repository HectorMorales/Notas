package com.htr.notas.notasapplication.controller;

import com.htr.notas.notasapplication.domain.Nota;
import com.htr.notas.notasapplication.service.INotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class NotaController {

    @Autowired
    private INotasService notaService;

    @GetMapping("/nota")
    public List<Nota> listarTodo(){
        return notaService.listarTodo();
    }

    @GetMapping("/nota/{id}")
    public ResponseEntity<Nota> listarId(@PathVariable("id") String id){
        Nota nota = notaService.listarId(Long.parseLong(id));
        return ResponseEntity.ok(nota);
    }

    @PostMapping("/nota")
    public ResponseEntity<Nota> agregar(@RequestBody Nota nota){
        notaService.agregar(nota);
        return ResponseEntity.ok(nota);
    }

    @PutMapping("/nota")
    public ResponseEntity<Nota> modificar(@RequestBody Nota nota){
        notaService.modificar(nota);
        return ResponseEntity.ok(nota);
    }

    @DeleteMapping("/nota")
    public void actualizar(@RequestBody Nota nota){
        notaService.eliminar(nota);
    }

}
