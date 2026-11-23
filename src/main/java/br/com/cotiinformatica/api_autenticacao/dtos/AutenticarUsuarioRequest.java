package br.com.cotiinformatica.api_autenticacao.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AutenticarUsuarioRequest(

        @Email(message = "Informe um endereço de email válido.")
        @NotBlank(message = "O email do usuário é obrigatório.")
        String email,

        @Pattern(
                regexp = "(?=.*\\p{Ll})(?=.*\\p{Lu})(?=.*[0-9])(?=.*[\\p{P}\\p{S}])\\S{8,}",
                message = "A senha deve conter pelo menos 1 letra minúscula, 1 letra maiúscula, 1 número, 1 caractere especial e pelo menos 8 caracteres."
        )
        @NotBlank(message = "A senha do usuário é obrigatória.")
        String senha
) {
}
