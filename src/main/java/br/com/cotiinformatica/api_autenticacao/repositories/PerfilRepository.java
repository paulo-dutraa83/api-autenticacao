package br.com.cotiinformatica.api_autenticacao.repositories;

import br.com.cotiinformatica.api_autenticacao.entities.Perfil;
import br.com.cotiinformatica.api_autenticacao.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {

    Perfil findByNome(String nome);
}
