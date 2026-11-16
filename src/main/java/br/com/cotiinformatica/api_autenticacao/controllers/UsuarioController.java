package br.com.cotiinformatica.api_autenticacao.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @PostMapping("criar")
    public ResponseEntity<?> criar() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("autenticar")
    public ResponseEntity<?> autenticar() {
        return ResponseEntity.ok().build();
    }
}
