/*
 * 
 * Copyright (c) 2018 Solace Corp.
 * 
 */
package org.springframework.cloud.stream.app.mylog.sink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.context.annotation.Bean;
import java.util.logging.Level;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

/**
 * A simple logger sink
 *
 * @author Solace Corp
 */
@EnableBinding(Sink.class)
@EnableConfigurationProperties(MylogSinkProperties.class)
public class MylogSinkConfiguration {
	@Autowired
	private MylogSinkProperties properties;

	// Imperative (procedural) style
	@Bean	
	@ServiceActivator(inputChannel = Sink.INPUT)	
	public LoggingHandler logSinkHandler() {
		LoggingHandler loggingHandler = new LoggingHandler(properties.getLevel().name());
		loggingHandler.setLogExpressionString("new String(payload)");
		return loggingHandler;
	}
}