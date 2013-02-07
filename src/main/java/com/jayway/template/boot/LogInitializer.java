package com.jayway.template.boot;

import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.jul.LevelChangePropagator;

public class LogInitializer {
    public static void initializeJavaUtilLoggingBridge() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        LevelChangePropagator propagator = new LevelChangePropagator();
        lc.addListener(propagator);
        propagator.resetJULLevels();
    }
}
