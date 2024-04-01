package com.gabriel.propostaapp.service;

import com.gabriel.propostaapp.DTOs.response.PropostaResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificacaoService {

    private final RabbitTemplate rabbitTemplate;

    public void notificar(PropostaResponseDTO proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

}
