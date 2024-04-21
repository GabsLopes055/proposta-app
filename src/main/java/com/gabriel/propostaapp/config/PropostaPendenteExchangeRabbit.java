package com.gabriel.propostaapp.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropostaPendenteExchangeRabbit {

    /*
    * Criar exchange para o microServiço de Proposta
    * */
    @Value("${rabbitmq.propostaPendente.exchange}")
    private String exchangePropostaPendente;

    /*
    * Cria fila para o serviço de proposta. Uma fila para receber a prospota e uma fila para notificar a proposta
    * */

    /*
    * Fila de analise
    * */
    @Bean
    public Queue filaPropostaPendenteMsAnaliseCredito() {
        return QueueBuilder.durable("proposta-pendente.ms-analise-credito").build();
    }

    /*
     * Fila de notificaçãow
     * */
    @Bean
    public Queue filaPropostaPendenteMsNotificacao() {
        return QueueBuilder.durable("proposta-pendente.ms-notificacao").build();
    }



    /*
    * Cria o binding da fila e da exchange
    */

    /*Binding da fila Analise de Credito para a exchange Proposta*/
    @Bean
    public Binding bindingPropostaPendenteMsAnaliseCredito() {
        return BindingBuilder.bind(filaPropostaPendenteMsAnaliseCredito()).
                to(fanoutExchangePropostaPendente());
    }

    /*Binding da fila de notificação para a exchange de analise de credito*/
    @Bean
    public Binding bindingPropostaPendenteMsNotificacao() {
        return BindingBuilder.bind(filaPropostaPendenteMsNotificacao()).
                to(fanoutExchangePropostaPendente());
    }

    /*
     * Cria o tipo da exchange
     * */
    @Bean
    public FanoutExchange fanoutExchangePropostaPendente() {
        return ExchangeBuilder.fanoutExchange(this.exchangePropostaPendente).build();
    }

}
