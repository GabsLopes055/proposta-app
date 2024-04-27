package com.gabriel.propostaapp.Agendador;

import com.gabriel.propostaapp.entity.Proposta;
import com.gabriel.propostaapp.repository.PropostaRepository;
import com.gabriel.propostaapp.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PropostaSemIntegracao {


    private final PropostaRepository repository;

    private final NotificacaoService notificacaoService;

    private final String exchange;

    public PropostaSemIntegracao(PropostaRepository repository,
                                 NotificacaoService notificacaoService,
                                 @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.repository = repository;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void buscarPropostasSemIntegracao() {

        repository.findAllByIntegradaIsFalse().forEach(proposta -> {
            try {
                notificacaoService.notificar(proposta.getUsuario(), exchange);
                atualizarProposta(proposta);
            } catch (RuntimeException ex) {
                throw new RuntimeException("Não foi possível realizar a notificação !");
            }
        });
    }

    private void atualizarProposta(Proposta proposta) {
        proposta.setIntegrada(true);
        repository.save(proposta);
    }

}
