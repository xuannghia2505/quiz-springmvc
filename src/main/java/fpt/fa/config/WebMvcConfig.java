package fpt.fa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    	registry.addResourceHandler("/assets/**") 
        .addResourceLocations("WEB-INF/assets/").setCachePeriod(31556926);
    	registry.addResourceHandler("/login/**") 
        .addResourceLocations("WEB-INF/views/login/").setCachePeriod(31556926);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
