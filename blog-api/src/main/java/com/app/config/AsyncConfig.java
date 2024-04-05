package com.app.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
	
	
	
	@Bean(name = "async-executor")
	public Executor asyncExecutor() {
		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		
		executor.setCorePoolSize(5); // Set the number of core threads
		executor.setMaxPoolSize(10); // Set the maximum number of threads
		executor.setQueueCapacity(25); //set queue capacity that waiting tasks
		executor.setThreadNamePrefix("Async-"); // set thread name
		executor.initialize(); // Initialize the executor
		
		return executor;
	}
	
}
