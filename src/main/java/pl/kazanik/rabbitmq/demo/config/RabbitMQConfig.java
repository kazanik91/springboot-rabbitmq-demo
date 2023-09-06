/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.rabbitmq.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Krysia
 */
@Configuration
public class RabbitMQConfig {
    
    @Value("${rabbitmq.queue.name}")
    private String queueName;
    
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    
    // spring bean for rabbitmq queue
    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }
    
    // spring bean for rabbitmq exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }
    
    // spring bean for rabbitmq queue and exchange binding
    @Bean
    public Binding queueToExchangeBinding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }
    
    // additionally to above spring beans we also need 3 more beans for rabbitmq:
    //
    //      ConnectionFactory
    //      RabbitMQTemplate
    //      RabbitMQAdmin
    //
    // but we can use spring boot auto configuration to handle it for us
    // and then just inject them whenever neccesary
}
