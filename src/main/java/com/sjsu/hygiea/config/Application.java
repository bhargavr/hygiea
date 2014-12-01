package com.sjsu.hygiea.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = "com.sjsu.hygiea.web", excludeFilters = { @Filter(Configuration.class) })
@EnableAutoConfiguration
//@PropertySource("classpath:application.properties")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
//	@Bean
//	public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
//		return new PropertySourcesPlaceholderConfigurer();
//	}
}
