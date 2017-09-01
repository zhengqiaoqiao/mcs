package com.yougou.mcs;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.yougou.mcs.domain.Statistics;
import com.yougou.mcs.metric.counter.BaseCounter;
import com.yougou.mcs.remote.IElasticsearchRemote;
import com.yougou.mcs.task.MethodTask;
import com.yougou.mcs.task.ServiceTask;
import com.yougou.mcs.util.ExportXLSUtil;
import com.yougou.mcs.util.JarUtil;

@Configuration
@ComponentScan
public class App {
	private final static Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
    	//获取spring容器
    	ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
    	//获取spring容器中的配置工厂
    	MetricRegistry metricRegistry = (MetricRegistry) context.getBean("metricRegistry");
    	HealthCheckRegistry healthCheckRegistry = (HealthCheckRegistry) context.getBean("healthCheckRegistry");
    	ScheduledReporter consoleReporter = (ScheduledReporter) context.getBean("consoleReporter");
    	//获取spring容器中的线程池
    	ThreadPoolTaskExecutor taskExecutor = context.getBean(ThreadPoolTaskExecutor.class);
    	//获取spring容器中的IElasticsearchRemote
    	IElasticsearchRemote elasticsearchRemote = context.getBean(IElasticsearchRemote.class);
    	try{
    		consoleReporter.start(3, TimeUnit.SECONDS);
    		final Counter counter = metricRegistry.counter("test");
    		for(int i=0; i< 10; i++){
    			taskExecutor.execute(new Runnable() {
    				@Override
    				public void run() {
    					for(int j=0; j< 10; j++){
    						LOGGER.info("增加");
    		    			counter.inc();
    		                try {
    							Thread.sleep(1000);
    						} catch (InterruptedException e) {
    							e.printStackTrace();
    						}
    		            }
    				}
    			});
    		}
    	}catch(Exception e){
    		LOGGER.error("程序运行发生异常！", e);
    	}finally{
    		taskExecutor.destroy();
    	}
    }
}
