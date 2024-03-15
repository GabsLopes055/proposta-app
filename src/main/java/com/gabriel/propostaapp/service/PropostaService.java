package com.gabriel.propostaapp.service;

import com.gabriel.propostaapp.DTOs.request.PropostaRequestDTO;
import com.gabriel.propostaapp.DTOs.response.PropostaResponseDTO;
import com.gabriel.propostaapp.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PropostaService {

    private final PropostaRepository repository;

    public PropostaResponseDTO criarProposta(PropostaRequestDTO request) {

        return null;
    }

}
