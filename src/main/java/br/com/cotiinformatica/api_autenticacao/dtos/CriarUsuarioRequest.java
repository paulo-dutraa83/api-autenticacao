package br.com.cotiinformatica.api_autenticacao.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CriarUsuarioRequest(

        @Size(min =6, max = 150, message = "O nome do usuário deve ter entre 6 e 150 caracteres")
        @NotBlank(message = "O nome do usuário é obrigatório")
        String nome,

        @Email(message =  "Informe um endereço de email válido")
        @NotBlank(message = "O email do usuário é obrigatório")
        String email,

        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "A senha deve ter no mínimo 8 caracteres, incluindo pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial")
        @NotBlank(message = "A senha do usuário é obrigatória")
        String senha

) {
}
