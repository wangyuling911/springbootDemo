package com.light.springboot.config;

import com.light.springboot.jwt.IJwtTokenService;
import com.light.springboot.jwt.JwtTokenService;
import com.light.springboot.verify.CsrfTokenVerifyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class CsrfTokenVerifyInterceptorConfiguration extends WebMvcConfigurerAdapter {

    @Value("${kf.csrf.jwt.secret.key:wangyulingwansui}")
    private String csrfJwtKey;

    @Value("#{'${kf.csrf.ignore.url}'.split(',')}")
    private String[] ignoreUrlPrefix;

    @Autowired
    private CsrfTokenVerifyInterceptor csrfTokenVerifyInterceptor;

    @Bean
    public IJwtTokenService jwtTokenService() {
        return new JwtTokenService(csrfJwtKey);
    }

    @Bean
    public CsrfTokenVerifyInterceptor csrfTokenVerifyInterceptor() {
        return new CsrfTokenVerifyInterceptor(csrfJwtKey, ignoreUrlPrefix);
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(csrfTokenVerifyInterceptor);
    }

}
