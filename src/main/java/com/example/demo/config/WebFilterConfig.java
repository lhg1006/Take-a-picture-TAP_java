package com.example.demo.config;


import com.example.demo.filter.IpLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebFilterConfig {

    @Bean
    public FilterRegistrationBean<IpLoggingFilter> loggingFilter() {
        FilterRegistrationBean<IpLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new IpLoggingFilter());
        registrationBean.addUrlPatterns("/*"); // 모든 URL 패턴에 대해 적용
        return registrationBean;
    }
}