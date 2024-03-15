package com.gabriel.propostaapp.controller;

import com.gabriel.propostaapp.DTOs.request.PropostaRequestDTO;
import com.gabriel.propostaapp.DTOs.response.PropostaResponseDTO;
import com.gabriel.propostaapp.service.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/api/v1/proposta")
@AllArgsConstructor
public class PropostaController {

    private final PropostaService service;


    @PostMapping
    public ResponseEntity<PropostaResponseDTO> criarProposta(@RequestBody PropostaRequestDTO request){

        PropostaResponseDTO response = this.service.criarProposta(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
