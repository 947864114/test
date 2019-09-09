package com.mq.service.impl;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * Description: TestMqServiceImpl
 * Created by LQZ
 * DATE: 2019/8/23 14:06
 */
public class TestMqServiceImpl implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long tag = message.getMessageProperties().getDeliveryTag();
        String body = new String(message.getBody());
        System.out.println(body);
//        if("1".equals(body)) {
            channel.basicAck(tag, false);
//        }
    }
}
