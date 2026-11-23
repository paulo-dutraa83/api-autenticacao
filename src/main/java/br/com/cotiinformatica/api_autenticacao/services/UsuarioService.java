package br.com.cotiinformatica.api_autenticacao.services;

import br.com.cotiinformatica.api_autenticacao.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.api_autenticacao.dtos.AutenticarUsuarioResponse;
import br.com.cotiinformatica.api_autenticacao.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.api_autenticacao.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.api_autenticacao.entities.Perfil;
import br.com.cotiinformatica.api_autenticacao.entities.Usuario;
import br.com.cotiinformatica.api_autenticacao.exceptions.AcessoNegadoException;
import br.com.cotiinformatica.api_autenticacao.exceptions.EmailJaCadastradoException;
import br.com.cotiinformatica.api_autenticacao.repositories.PerfilRepository;
import br.com.cotiinformatica.api_autenticacao.repositories.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HexFormat;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    //Metodo para implementar o fluxo de cadastro
    public CriarUsuarioResponse criarUsuario(CriarUsuarioRequest request) {

        //Verificando se o email ja esta cadastrado no banco de dados
        if(usuarioRepository.findByEmail(request.email()) != null) {
            throw new EmailJaCadastradoException();
        }

        //Capturando os dados recebidos e criando uma instancia da classe de entidade
        var usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(criptografarSenha(request.senha()));

        //Todo usuário que se cadastrar será vinculado a perfil chamado "Operador"
        var perfil = perfilRepository.findByNome("Operador");

        //Se o perfil não existir, iremos cadstra-lo
        if(perfil == null) {
            perfil = new Perfil();
            perfil.setNome("Operador");
            perfilRepository.save(perfil);
        }

        usuario.setPerfil(perfil);

        //Salvando o usuario no banco de dados
        usuarioRepository.save(usuario);

        //Retornando os dados do cliente
        return new CriarUsuarioResponse(
                "Usuario cadastrado com sucesso!",
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil().getNome(),
                LocalDateTime.now()
        );
    }

    //Método para implementaro fluxo de autenticação do usuário
    public AutenticarUsuarioResponse autenticarUsuario(AutenticarUsuarioRequest request) {


        //Procurando o usuário no banco de dados pelo email
        var usuario = usuarioRepository.findByEmail(request.email());

        //Verificando se o usuario foi encontrado e se a senha informada é igual a senha cadastrada no banco de dados
        if(usuario != null && usuario.getSenha().equals(criptografarSenha(request.senha()))) {

            //Retornando os dados do usuario autenticado
            return new AutenticarUsuarioResponse(
                    "Usuario autenticado com sucesso!",
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getPerfil().getNome(),
                    LocalDateTime.now(),
                    gerarToken(usuario)
            );

        } else {
            throw new AcessoNegadoException();
        }
    }


    //Método privado auxiliar para fazer a criptografia da senha do usuário
    private String criptografarSenha(String senha) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = sha256.digest(senha.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 indisponível no ambiente Java.", e);
        }
    }

    //Metodo provado auxuliar para gerar o token JWT
    private String gerarToken(Usuario usuario) {

        //Chave para criptografar e assinar o token
        var chaveAssinatura = "da7a8711-7a4f-426f-a025-201806c660c9";

        //Gerar o TOKEN JWT:
        Instant agora = Instant.now();
        Instant dataExpiracao = agora.plusMillis(3600000); //1 hora

        return Jwts.builder()
                .setSubject(usuario.getEmail()) //Usuário que gerou o TOKEN JWT
                .claim("role", usuario.getPerfil().getNome()) //Perfil do usuário
                .setIssuedAt(Date.from(agora)) //Data e hora de geração do TOKEN
                .setExpiration(Date.from(dataExpiracao)) //Data de exíração do token
                .signWith(SignatureAlgorithm.HS256, chaveAssinatura) //Chave secreta para criptografia
                .compact(); //Retornar o TOKEN

    }

}
