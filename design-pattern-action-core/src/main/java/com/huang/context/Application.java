package com.huang.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan(basePackages = {"com.huang"})
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws ClassNotFoundException {
        SpringApplication.run(Application.class, args);
        logger.info("------design-pattern-action-core start success!!!---------");
    }
}