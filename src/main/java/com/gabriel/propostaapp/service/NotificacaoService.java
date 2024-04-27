package com.gabriel.propostaapp.service;

import com.gabriel.propostaapp.DTOs.response.PropostaResponseDTO;
import com.gabriel.propostaapp.entity.Proposta;
import com.gabriel.propostaapp.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificacaoService {

    private final RabbitTemplate rabbitTemplate;

    public void notificar(Usuario usuario, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", usuario);
    }

    public void analisarProposta(Proposta proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

}
