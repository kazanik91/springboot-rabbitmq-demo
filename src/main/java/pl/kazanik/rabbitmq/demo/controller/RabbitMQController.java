/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.rabbitmq.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kazanik.rabbitmq.demo.producer.RabbitMQProducer;

/**
 *
 * @author Krysia
 */
@RestController
@RequestMapping(value = "/api/rabbit-demo")
public class RabbitMQController {
    
    private RabbitMQProducer rabbitProducer;

    public RabbitMQController(RabbitMQProducer rabbitProducer) {
        this.rabbitProducer = rabbitProducer;
    }
    
    @GetMapping(path = "/publish")
    public ResponseEntity<String> publishMessage(
            @RequestParam("message") String message) {
        rabbitProducer.sendMessage(message);
        return ResponseEntity.ok("Message published to the rabbit mq...");
    }
}
