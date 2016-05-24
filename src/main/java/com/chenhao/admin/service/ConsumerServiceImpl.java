package com.chenhao.admin.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


@Service("consumerService")
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 接收消息
     */
    @Override
    public TextMessage receive(Destination destination) {
        TextMessage tm = (TextMessage) jmsTemplate.receive(destination);
        return tm;

    }

}