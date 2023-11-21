package com.Bgy.CHL.AdminTareas.ControllerTareas;


import com.Bgy.CHL.AdminTareas.Domain.Usuario.DatosAutenticacionUsuario;
import com.Bgy.CHL.AdminTareas.Domain.Usuario.Usuario;
import com.Bgy.CHL.AdminTareas.infra.Security.DatosJWTToken;
import com.Bgy.CHL.AdminTareas.infra.Security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutentificationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

        @PostMapping
        public ResponseEntity AutenticarUsuario(@RequestBody @Valid  DatosAutenticacionUsuario datosAutenticacionUsuario){

            Authentication authtoken= new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.usuario(),datosAutenticacionUsuario.password());
           var usuarioAutenticado=  authenticationManager.authenticate(authtoken) ;
            var JWTToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
            return ResponseEntity.ok(new DatosJWTToken(JWTToken));
    }
}
