/**
 * 
 */
package com.sjsu.hygiea.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @author bhargav
 * 
 */
@Configuration
<<<<<<< HEAD
public class MvcConfig extends WebMvcConfigurerAdapter
{

	@Override
	public void addViewControllers(final ViewControllerRegistry registry)
	{
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/associateRewards").setViewName("AssociateRewards");
	}
=======
public class MvcConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/CreateReward").setViewName("CreateReward");
    }
>>>>>>> 5bd31c224bfe8116fa959b4fbe6620b46d8b23be

}
