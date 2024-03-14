package com.gabriel.propostaapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_proposta")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorSolicitado;

    private int prazoPagamento;

    private Boolean aprovada;

    private boolean integrada;

    private String descricao;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
