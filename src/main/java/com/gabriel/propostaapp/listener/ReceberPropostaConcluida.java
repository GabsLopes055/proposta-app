package com.gabriel.propostaapp.listener;

import com.gabriel.propostaapp.DTOs.response.PropostaResponseDTO;
import com.gabriel.propostaapp.config.WebSocketConfiguration;
import com.gabriel.propostaapp.entity.Proposta;
import com.gabriel.propostaapp.mapper.PropostaMapper;
import com.gabriel.propostaapp.repository.PropostaRepository;
import com.gabriel.propostaapp.service.WebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ReceberPropostaConcluida {

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private WebSocketService service;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.conncluida}")

    public void receberPropostaConcluida(Proposta proposta) {
        repository.save(proposta);

        service.notificarFrontEnd(PropostaMapper.INSTANCE.convertEntityToDto(proposta));
    }

}
