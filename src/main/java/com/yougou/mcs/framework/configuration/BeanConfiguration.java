package com.yougou.mcs.framework.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


import metrics_influxdb.Influxdb;
import metrics_influxdb.InfluxdbReporter;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.yougou.mcs.remote.IElasticsearchRemote;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;


@Configuration
public class BeanConfiguration {
	private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BeanConfiguration.class);
	@Bean(name="userProperties")  
    public Properties initProperties() throws Exception { 
		String userDir = System.getProperty("user.dir");
		String filePath = userDir + File.separator + "config/application.properties";
		Properties pro = new Properties();
		FileInputStream in = new FileInputStream(filePath);
		pro.load(in);
		LOGGER.info("初始化用户自定义配置完成！");
		return pro;
    }  
	
    @Bean  
    public IElasticsearchRemote initElasticsearchRemote() { 
    	IElasticsearchRemote remote =  Feign.builder().encoder(new JacksonEncoder())
    			.decoder(new JacksonDecoder())
    			.logger(new Logger.ErrorLogger())
    			.logLevel(Logger.Level.BASIC)
    			.target(IElasticsearchRemote.class, "http://10.10.10.88:9200/dubbo.invoke");
    	LOGGER.info("初始化IElasticsearchRemote完成！");
    	return remote;
    }  
    
    @Bean(name="taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(3);
        //最大线程数
        executor.setMaxPoolSize(30);
        //队列最大长度
        executor.setQueueCapacity(50);
        //线程池维护线程所允许的空闲时间
        executor.setKeepAliveSeconds(1800);
        //CallerRunsPolicy是“调用者运行”策略，实现了一种调节机制 。它不会抛弃任务，也不会抛出异常。 而是将任务回退到调用者。它不会在线程池中执行任务，而是在一个调用了Executor的线程中执行该任务。
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        LOGGER.info("初始化线程池完成！");
        return executor;
    }
    
    @Bean(name = "healthCheckRegistry")
    public HealthCheckRegistry healthCheckRegistry() throws Exception {
    	HealthCheckRegistry registry = new HealthCheckRegistry();
    	return registry;
    }
    
    @Bean(name = "metricRegistry")
    public MetricRegistry metricRegistry() throws Exception {
    	MetricRegistry metricRegistry = new MetricRegistry();
    	return metricRegistry;
    }
    
    @Bean(name = "influxdbReporter")
    public ScheduledReporter influxdbReporter(MetricRegistry metrics, Properties userProperties) throws Exception {
    	String db_host = userProperties.get("db.host") == null ? "" : userProperties.get("db.host").toString();
    	int db_port = userProperties.get("db.port") == null ? 2000 : Integer.parseInt(userProperties.get("db.port").toString());
    	String db_username = userProperties.get("db.username") == null ? "" : userProperties.get("db.username").toString();
    	String db_password = userProperties.get("db.password") == null ? "" : userProperties.get("db.password").toString();
    	String db_database = userProperties.get("db.database") == null ? "" : userProperties.get("db.database").toString();
        return InfluxdbReporter.forRegistry(metrics).build(new Influxdb(db_host, db_port, db_database, db_username, db_password, TimeUnit.SECONDS));
    }
    
    @Bean(name = "consoleReporter")
    public ScheduledReporter consoleReporter(MetricRegistry metrics) throws Exception {
    	return ConsoleReporter.forRegistry(metrics).build();
    }
}
