package com.example.demo.filter;

import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class IpLoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 초기화 코드
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 요청 처리 전에 로그를 남깁니다.
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ipAddress = httpRequest.getHeader("X-Forwarded-For");

        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = httpRequest.getRemoteAddr();
        }
        if(!ipAddress.equals("127.0.0.1")){
            System.out.println("Request from IP: " + ipAddress);
        }

        // 다음 단계로 진행
        chain.doFilter(request, response);

        // 응답 처리 후에 추가적인 로직을 수행할 수 있습니다.
    }

    @Override
    public void destroy() {
        // 필터 종료 시 처리할 코드
    }
}
