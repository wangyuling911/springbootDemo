package com.wyl.provide.Interceptor;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("=======初始化过滤器=========");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        System.out.println("filter 耗时：" + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {
        System.out.println("=======销毁过滤器=========");
    }

}
