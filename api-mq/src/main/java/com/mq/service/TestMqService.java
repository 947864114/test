package com.mq.service;

import com.rabbitmq.client.Channel;

/**
 * Description: TestMqServic
 * Created by LQZ
 * DATE: 2019/8/23 14:06
 */
public interface TestMqService {
    public int testService(String message, Channel channel);
}
