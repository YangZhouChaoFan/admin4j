package com.chenhao.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class Test2 {

    private static final Logger logger = LoggerFactory.getLogger(Test2.class);

    @Autowired
    private Environment env;

    @Test
    public void test() {
        logger.info(env.getProperty("jdbc.url"));
    }

}
