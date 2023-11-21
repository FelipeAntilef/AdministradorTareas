package com.Bgy.CHL.AdminTareas.ControllerTareas;


import com.Bgy.CHL.AdminTareas.Domain.tareas.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tareas")
public class TareasController {

    @Autowired
    private TareaRepository tareaRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestasTareas> TareasAgendadas(@RequestBody @Valid DatosRegistroTareas datosRegistroTareas,
                                                                 UriComponentsBuilder uriComponentsBuilder){
        Tarea tarea= tareaRepository.save(new Tarea(datosRegistroTareas));
        DatosRespuestasTareas datosRespuestasTareas = new DatosRespuestasTareas(tarea.getId(), tarea.getCreador(), tarea.getTitulo(),tarea.getEstado() ,tarea.getDescripcion());
        URI url= uriComponentsBuilder.path("/tareas/{id}").buildAndExpand(tarea.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestasTareas) ;
    }
    @GetMapping
    public ResponseEntity<Page<DatosListadoTareas>> TareasGuardadas(@PageableDefault(size = 6) Pageable paginacion){
        //return tareaRepository.findAll(paginacion).map(DatosListadoTareas::new);
        return ResponseEntity.ok(tareaRepository.findByMostrarTrue(paginacion).map(DatosListadoTareas::new));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity TareasEliminar(@PathVariable Long id){
        Tarea tarea = tareaRepository.getReferenceById(id);
        tarea.DesactivarTarea();
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTarea(@RequestBody @Valid DatosActualizarTarea datosActualizarTarea){
        Tarea tarea = tareaRepository.getReferenceById(datosActualizarTarea.id());
        tarea.actualizarDatos(datosActualizarTarea);
        return ResponseEntity.ok(new DatosRespuestasTareas(tarea.getId(), tarea.getCreador(), tarea.getTitulo(),tarea.getEstado() ,tarea.getDescripcion()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestasTareas> RetornaDatosTareas(@PathVariable Long id){
        Tarea tarea = tareaRepository.getReferenceById(id);
        var datosTareas= new DatosRespuestasTareas(tarea.getId(), tarea.getTitulo(),tarea.getDescripcion() ,tarea.getEstado(), tarea.getCreador());
        return ResponseEntity.ok(datosTareas);

    }
}
