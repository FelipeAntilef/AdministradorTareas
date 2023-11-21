package com.Bgy.CHL.AdminTareas.Domain.tareas;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTarea(@NotNull Long id, String Titulo, String descripcion, Estado estado) {

}
