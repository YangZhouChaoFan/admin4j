package com.chenhao.admin.filter;

import com.chenhao.admin.exception.TokenException;
import com.chenhao.admin.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.deploy.xml.BadTokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenhao on 2016/4/3.
 */
public class TokenFilter extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURL().indexOf("login") == -1) {

            String token = request.getHeader("token");

            ShardedJedis shardedJedis = shardedJedisPool.getResource();
            String tokenKey = "token:" + token;
            try {
                if (!shardedJedis.exists(tokenKey)) {
                    throw new TokenException();
                } else {
                    String userId = shardedJedis.hget(tokenKey, "userId");
                    request.setAttribute("userId", userId);
                    return true;
                }
            } finally {
                shardedJedis.close();
            }

        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}
