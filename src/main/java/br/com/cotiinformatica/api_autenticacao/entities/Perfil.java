package br.com.cotiinformatica.api_autenticacao.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "perfis")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(length = 25, nullable = false, unique = true)
    private String nome;
}
