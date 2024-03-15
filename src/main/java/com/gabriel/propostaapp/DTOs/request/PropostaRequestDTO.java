package com.gabriel.propostaapp.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropostaRequestDTO {

    private String nome;

    private String sobrenome;

    private String telefone;

    private String cpf;

    private String renda;

    private Double valorSolicitado;

    private int prazoPagamento;


}
