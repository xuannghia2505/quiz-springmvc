package fpt.fa.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("fpt.fa.*")

@EnableTransactionManagement

// Load to Environment.


public class ApplicationContextConfig {

   // Lưu trữ các giá thuộc tính load bởi @PropertySource.
   @Autowired
   private Environment env;

   @Bean(name = "viewResolver")
   public InternalResourceViewResolver getViewResolver() {
       InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

       viewResolver.setPrefix("/WEB-INF/views/");
       viewResolver.setSuffix(".jsp");

       return viewResolver;
   }

   

}