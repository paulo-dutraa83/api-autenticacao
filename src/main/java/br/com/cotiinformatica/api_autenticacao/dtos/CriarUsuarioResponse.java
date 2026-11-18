package br.com.cotiinformatica.api_autenticacao.dtos;

import java.time.LocalDateTime;

public record CriarUsuarioResponse(

        String  mensagem,
        Integer usuarioId,
        String  nome,
        String  email,
        String  perfil,
        LocalDateTime dataHoraCadastro
) {
}
