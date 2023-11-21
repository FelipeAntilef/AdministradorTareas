package com.Bgy.CHL.AdminTareas.Domain.tareas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "tb_tareas")
@Entity(name="tb_tarea")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha;
    private String creador;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private boolean mostrar;


    public Tarea(DatosRegistroTareas datosRegistroTareas) {
        this.creador=datosRegistroTareas.creador();
        this.descripcion= datosRegistroTareas.descripcion();
        this.titulo= datosRegistroTareas.Titulo();
        this.estado=datosRegistroTareas.estado();
        this.fecha= LocalDateTime.now();
        this.mostrar=true;
    }

    public void actualizarDatos(DatosActualizarTarea datosActualizarTarea) {
        if(datosActualizarTarea.Titulo() != null){
            this.titulo= datosActualizarTarea.Titulo();
        }
        if (datosActualizarTarea.descripcion() != null){
            this.descripcion= datosActualizarTarea.descripcion();

        }
        if(datosActualizarTarea.estado() != null){
            this.estado=datosActualizarTarea.estado();
        }
    }

    public void DesactivarTarea() {
        this.mostrar=false;
    }
}
