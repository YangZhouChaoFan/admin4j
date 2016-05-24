package com.chenhao.admin.controller;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import com.chenhao.admin.model.Response;
import com.chenhao.admin.service.ConsumerService;
import com.chenhao.admin.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/queueController")
public class QueueController {

    private static final Logger logger = LoggerFactory.getLogger(QueueController.class);

    //队列名queue
    @Autowired
    private Destination queueDestination;

    //队列消息生产者
    @Autowired
    private ProducerService producerService;

    //队列消息消费者
    @Autowired
    private ConsumerService consumerService;

    @RequestMapping("/send")
    @ResponseBody
    public Response producer(@RequestBody Map<String, Object> map) {
        logger.info("发送消息到队列queue");
        producerService.sendMessage(queueDestination, (String) map.get("message"));
        return new Response().success();
    }

    @RequestMapping("/receive")
    @ResponseBody
    public Response queue_receive() throws JMSException {
        logger.info("获取消息从队列queue");
        TextMessage tm = consumerService.receive(queueDestination);
        if (tm == null) {
            return new Response().failure("暂无数据");
        }
        return new Response().success(tm.getText());
    }

}