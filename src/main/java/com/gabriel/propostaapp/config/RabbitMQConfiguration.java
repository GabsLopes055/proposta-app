package com.gabriel.propostaapp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

//    private ConnectionFactory connectionFactory;
//
//    public RabbitMQConfiguration(ConnectionFactory connectionFactory) {
//        this.connectionFactory = connectionFactory;
//    }

    @Bean
    public Queue propostaPendenteMsAnaliseCredito() {
        return QueueBuilder.durable("proposta-pendente.ms-analise-credito").build();
    }

    @Bean
    public Queue propostaPendenteMsNotificacao() {
        return QueueBuilder.durable("proposta-pendente.ms-notificacao").build();
    }

    @Bean
    public Queue propostaConcluidaMsProposta() {
        return QueueBuilder.durable("proposta-concluida.ms-proposta").build();
    }

    @Bean
    public Queue propostaConcluidaMsNotificacao() {
        return QueueBuilder.durable("proposta-concluida.ms-notificacao").build();
    }


    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializarAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public FanoutExchange fanoutExchangePropostaPendente() {
        return ExchangeBuilder.fanoutExchange("proposta-pendente.ex").build();
    }

    @Bean
    public Binding bindingPropostaPendenteMsAnaliseCredito() {
        return BindingBuilder.bind(propostaPendenteMsAnaliseCredito()).to(fanoutExchangePropostaPendente());
    }

    @Bean
    public Binding bindingPropostaPendenteMsNotificacao() {
        return BindingBuilder.bind(propostaPendenteMsNotificacao()).to(fanoutExchangePropostaPendente());
    }
}
