package com.sjsu.hygiea.config;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = "com.sjsu.hygiea", excludeFilters = { @Filter(Configuration.class) })
@EnableAutoConfiguration
@EnableTransactionManagement
//@PropertySource("classpath:application.properties")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
	@Bean
	public DataSource dataSource() {
//		EmbeddedDatabaseFactory factory = new EmbeddedDatabaseFactory();
//		factory.setDatabaseName("spring-social-showcase");
//		factory.setDatabaseType(EmbeddedDatabaseType.H2);
//		return factory.getDatabase();
		
		DriverManagerDataSource mysqldataSource = new DriverManagerDataSource();
	    mysqldataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    mysqldataSource.setUrl("jdbc:mysql://localhost:3306/hygiea?useConfigs=maxPerformance&characterEncoding=utf8");
	    mysqldataSource.setUsername("hygiea_u");
	    mysqldataSource.setPassword("hygiea_p@$sw0rD");
	    return mysqldataSource; 
		
	}
    
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
//	@Bean
//	public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
//		return new PropertySourcesPlaceholderConfigurer();
//	}
}
