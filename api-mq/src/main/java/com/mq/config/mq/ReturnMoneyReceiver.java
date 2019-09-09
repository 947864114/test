package com.mq.config.mq;

import com.alibaba.fastjson.JSONObject;
import com.mq.config.UrlProperties;
import com.mq.restClient.ReturnMoneyClient;
import com.mq.util.AppJson;
import com.mq.util.exception.KDException;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ReturnMoneyReceiver {

    @Autowired
    UrlProperties urlProperties;
    @Autowired
    ReturnMoneyClient returnMoneyClient;


    @RabbitHandler
    @RabbitListener(queues = ReturnMoneyQueue.QUEUE_NAME)
    public void returnMoney(JSONObject jsonObject, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        //bl发送请求
        String url = urlProperties.getBl();

        AppJson appJson = returnMoneyClient.returnMoney(url, jsonObject);

        if (appJson.getCode() != AppJson.CODESTATUSSUCCESS) {
            throw new KDException(appJson.getCode(), appJson.getMessage());
        }

        try {
            channel.basicAck(tag, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
