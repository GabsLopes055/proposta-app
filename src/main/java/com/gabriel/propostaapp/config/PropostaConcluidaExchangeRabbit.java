package com.gabriel.propostaapp.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropostaConcluidaExchangeRabbit {

    /*Cria exchange para proposta concluida*/
    @Value("${rabbitmq.propostaConcluida.exchange}")
    private String exchangePropostaConcluida;

    /*Cria as filas para analizar a proposta e informar que a proposta esta concluida*/

    /*Fila para analizar a proposta*/
    @Bean
    public Queue propostaConcluidaMsProposta() {
        return QueueBuilder.durable("proposta-concluida.ms-proposta").build();
    }

    /*Fila para notificar que a proposta esta concluida*/
    @Bean
    public Queue propostaConcluidaMsNotificacao() {
        return QueueBuilder.durable("proposta-concluida.ms-notificacao").build();
    }


    /*Criando os bindings para as fila de proposta concluida, um binding para receber a proposta para analise e outro para notificar a conclusao da proposta*/

    /*Binding para analisar a proposta*/
    @Bean
    public Binding bindingPropostaConcluidaMSPropostaApp() {
        return BindingBuilder.bind(propostaConcluidaMsProposta()).
                to(fanoutExchangePropostaConcluida());
    }

    /*Binding para notificar que a proposta esta concluida*/
    @Bean
    public Binding bindingPropostaConcluidaMsNotificacao() {
        return BindingBuilder.bind(propostaConcluidaMsNotificacao())
                .to(fanoutExchangePropostaConcluida());
    }


    /*Criando o tipo da exchange*/
    @Bean
    public FanoutExchange fanoutExchangePropostaConcluida() {
        return ExchangeBuilder.fanoutExchange(exchangePropostaConcluida).build();
    }

}
