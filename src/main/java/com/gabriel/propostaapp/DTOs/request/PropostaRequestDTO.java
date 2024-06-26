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

    private String email;

    private String cpf;

    private String telefone;

    private Double renda;

    private Double valorSolicitado;

    private int prazoPagamento;


}
