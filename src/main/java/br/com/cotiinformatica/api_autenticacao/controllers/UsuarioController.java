package br.com.cotiinformatica.api_autenticacao.controllers;

import br.com.cotiinformatica.api_autenticacao.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.api_autenticacao.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.api_autenticacao.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("criar")
    public ResponseEntity<?> criar(@RequestBody @Valid CriarUsuarioRequest request) {

        var response = usuarioService.criarUsuario(request);
        //HTTP 201 - Created
        return ResponseEntity.status(201).body(response);

    }

    @PostMapping("autenticar")
    public ResponseEntity<?> autenticar(@RequestBody @Valid AutenticarUsuarioRequest request) {
        var response = usuarioService.autenticarUsuario(request);
        //HTTP 200 - OK
        return ResponseEntity.status(200).body(response);
    }
}
