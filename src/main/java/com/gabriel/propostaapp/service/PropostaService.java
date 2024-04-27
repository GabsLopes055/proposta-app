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

    private final String exchangeNotificar;

    private final String exchangeAnalisar;


    public PropostaService(PropostaRepository repository,
                           NotificacaoService notificacaoService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchangeNotificar,
                           @Value("${rabbitmq.propostaConcluida.exchange}") String exchangeAnalisar) {
        this.repository = repository;
        this.notificacaoService = notificacaoService;
        this.exchangeNotificar = exchangeNotificar;
        this.exchangeAnalisar = exchangeAnalisar;
    }

    public PropostaResponseDTO criarProposta(PropostaRequestDTO request) {

        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToEntity(request);

        this.repository.save(proposta);

        this.notificarRabbitMQ(proposta);
        this.analisarPropostaRabbitMQ(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);

    }

    public List<PropostaResponseDTO> listarPropostas() {

        return PropostaMapper.INSTANCE.convertListEntityToListDto(repository.findAll());

    }

    public void notificarRabbitMQ(Proposta proposta) {
        try {
            this.notificacaoService.notificar(proposta.getUsuario(), exchangeNotificar);
        } catch (RuntimeException ex) {
            proposta.setIntegrada(false);
            this.repository.save(proposta);
        }
    }

    public void analisarPropostaRabbitMQ(Proposta proposta) {
        try {
            System.out.println(proposta.toString());
            this.notificacaoService.analisarProposta(proposta, exchangeNotificar);
        } catch (RuntimeException ex) {
            proposta.setIntegrada(false);
            this.repository.save(proposta);
        }
    }

}
