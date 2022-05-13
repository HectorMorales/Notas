package com.htr.notas.notasapplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NotaDTO {

    private Long id;
    public String nota;
    public Date fecha;
}
