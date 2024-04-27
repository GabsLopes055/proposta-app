package com.gabriel.propostaapp.service;

import com.gabriel.propostaapp.DTOs.response.PropostaResponseDTO;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private SimpMessagingTemplate template;

    public void notificarFrontEnd(PropostaResponseDTO responseDTO) {
            template.convertAndSend("/propostas", responseDTO);
    }

}
