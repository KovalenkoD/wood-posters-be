package com.woodposters.config.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);


    @Override
    public void sessionCreated(HttpSessionEvent event) {
        logger.info("session created");
        event.getSession().setMaxInactiveInterval(360*60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {

        logger.info("session destroyed");
    }
}