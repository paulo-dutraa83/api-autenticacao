package br.com.cotiinformatica.api_autenticacao.exceptions;

public class AcessoNegadoException extends RuntimeException {

    //Metodo para retornar uma mensgem de erro padrão
    public String getMessage() {
        return "Acesso negado. Usuario Inválido.";
    }
}
