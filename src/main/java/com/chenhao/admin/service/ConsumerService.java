package com.chenhao.admin.service;

import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 * Created by chenhao on 2016/5/24.
 */
public interface ConsumerService {
    TextMessage receive(Destination queueDestination);
}
