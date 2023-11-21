package com.Bgy.CHL.AdminTareas.Domain.tareas;

public record DatosListadoTareas(Long id,String Titulo, String descripcion, String estado) {
    public DatosListadoTareas(Tarea tarea){
        this(tarea.getId(),tarea.getTitulo(), tarea.getDescripcion(), tarea.getEstado().toString());
    }
}
