package com.gabriel.propostaapp.service;

import com.gabriel.propostaapp.DTOs.request.PropostaRequestDTO;
import com.gabriel.propostaapp.DTOs.response.PropostaResponseDTO;
import com.gabriel.propostaapp.entity.Proposta;
import com.gabriel.propostaapp.mapper.PropostaMapper;
import com.gabriel.propostaapp.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private final PropostaRepository repository;

    private final NotificacaoService notificacaoService;

    private final String exchange;


    public PropostaService(PropostaRepository repository,
                           NotificacaoService notificacaoService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.repository = repository;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;
    }

    public PropostaResponseDTO criarProposta(PropostaRequestDTO request) {

        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToEntity(request);

        this.repository.save(proposta);

        this.notificarRabbitMQ(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);

    }

    public List<PropostaResponseDTO> listarPropostas() {

        return PropostaMapper.INSTANCE.convertListEntityToListDto(repository.findAll());

    }

    public void notificarRabbitMQ(Proposta proposta) {
        try {
            this.notificacaoService.notificar(proposta, exchange);
        } catch (RuntimeException ex) {
            proposta.setIntegrada(false);
            this.repository.save(proposta);
        }
    }

}
