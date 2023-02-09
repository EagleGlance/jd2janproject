package com.noirix.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupCustomListener implements ServletContextListener {
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        ServletContextListener.super.contextInitialized(sce);
//        //here logic of DB start, caches start, etc.
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        ServletContextListener.super.contextDestroyed(sce);
//        //close connection with all external services
//    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context Is UP");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context Is DOWN");
    }
}