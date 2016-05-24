package com.chenhao.admin.service;

import javax.jms.Destination;

/**
 * Created by chenhao on 2016/5/24.
 */
public interface ProducerService {
    void sendMessage(Destination queueDestination, String message);

    void sendMessage(final String msg);
}
