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
        registry.addViewController("/CreateReward").setViewName("CreateReward");
        registry.addViewController("/GetRewardsCatalog").setViewName("RewardsCatalog");
        registry.addViewController("/RegisterUser").setViewName("RegisterUser");
        registry.addViewController("/registerDevice").setViewName("device");
        registry.addViewController("/personalRewards").setViewName("rewards");
        
	}

}
