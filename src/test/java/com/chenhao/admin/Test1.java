package com.chenhao.admin;

import com.alibaba.fastjson.JSON;
import com.chenhao.admin.dao.RoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by chenhao on 2015/12/1.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})

public class Test1 {

    private static final Logger logger = LoggerFactory.getLogger(Test1.class);

    @Autowired
    private RoleMapper roleDao;

    @Test
    public void test1() {
        String str = JSON.toJSONString(roleDao.selectByPrimaryKey(1));
        logger.info(str);
    }

}
