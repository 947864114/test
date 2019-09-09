package com.mq.config.mq;

import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Getter
@Setter
public class RabbitMqConfig {

    @Autowired
    private AmqpAdmin amqpAdmin;

    //    @Value("${service.api-common.url}")
//    private String apiCommonUrl;
//    @Value("${service.api-alarm-message.url}")
//    private String apiAlarmMessageUrl;
//    @Value("${service.api-mq-producer.url}")
//    private String apiMqProducerUrl;
//    @Value("${service.api-workorder.url}")
//    private String apiWorkOrderUrl;
    @Value("${spring.profiles.active}")
    private String activeEnv;

    @PostConstruct
    public void declare() {
        declare(ReturnMoneyQueue.EXCHANGE_NAME, ReturnMoneyQueue.QUEUE_NAME);
    }

    private void declare(String exchangeName, String queueName) {
        Queue queue = new Queue(queueName, true);
        DirectExchange directExchange =
                new DirectExchange(exchangeName, true, false);
        Binding binding =
                BindingBuilder.bind(queue).to(directExchange).with(queueName);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(binding);
    }

}
