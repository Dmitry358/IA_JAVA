package com.example.webapp;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {
  @Bean
  public ServletRegistrationBean<ProcessServlet> processServlet() {
    return new ServletRegistrationBean<>(new ProcessServlet(), "/process");
  }
}
