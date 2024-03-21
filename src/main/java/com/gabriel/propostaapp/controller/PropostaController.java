package com.gabriel.propostaapp.controller;

import com.gabriel.propostaapp.DTOs.request.PropostaRequestDTO;
import com.gabriel.propostaapp.DTOs.response.PropostaResponseDTO;
import com.gabriel.propostaapp.service.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "proposta")
@AllArgsConstructor
public class PropostaController {

    private final PropostaService service;

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> criarProposta(@RequestBody PropostaRequestDTO request) {

        PropostaResponseDTO response = this.service.criarProposta(request);

        return ResponseEntity.created((ServletUriComponentsBuilder.fromCurrentRequest()).path("/{id}").buildAndExpand(response.getId()).toUri()).body(response);

    }

    @GetMapping
    public ResponseEntity<List<PropostaResponseDTO>> listarPropostas() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarPropostas());
    }

}
