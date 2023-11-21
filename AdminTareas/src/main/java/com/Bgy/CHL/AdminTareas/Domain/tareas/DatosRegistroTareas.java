package com.Bgy.CHL.AdminTareas.Domain.tareas;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;



public record DatosRegistroTareas(
        @NotBlank
        String Titulo,
        @NotBlank
        String descripcion,

        LocalDate fecha,
        @NotNull
        Estado estado,
        @NotBlank
        String creador){

}

