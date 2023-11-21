package com.Bgy.CHL.AdminTareas.infra.Errores;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.query.NativeQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity TratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity TratarError400(MethodArgumentNotValidException e){
    var errores = e.getFieldErrors().stream().map(DatosErrorValidation::new).toList();

        return ResponseEntity.badRequest().body(errores );
    }

    private record DatosErrorValidation(String Campo,String error ){

        public DatosErrorValidation(FieldError Error){

            this(Error.getField(),Error.getDefaultMessage());
        }


    }
}
