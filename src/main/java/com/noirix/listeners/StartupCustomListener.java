package com.noirix.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupCustomListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context Is UP");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context Is DOWN");
    }
}