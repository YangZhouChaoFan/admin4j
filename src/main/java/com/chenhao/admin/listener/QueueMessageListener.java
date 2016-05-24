package com.chenhao.admin.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


public class QueueMessageListener implements MessageListener {

    static private Logger logger = LoggerFactory.getLogger(QueueMessageListener.class);


    //当收到消息后，自动调用该方法
    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        if (tm != null) {
            try {
                String msg = tm.getText();
                logger.info("获取消息：" + msg);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

}