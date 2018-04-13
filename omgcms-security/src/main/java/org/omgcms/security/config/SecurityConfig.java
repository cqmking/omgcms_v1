package org.omgcms.security.config;

import org.omgcms.security.service.CustomUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 22:15 2018/4/5
 * @Modified By
 * <p>
 * /**
 * Created by PrimaryKey on 17/2/4.
 * @EnableWebSecurity: 禁用Boot的默认Security配置，配合@Configuration启用自定义配置（需要扩展WebSecurityConfigurerAdapter）
 * @EnableGlobalMethodSecurity(prePostEnabled = true): 启用Security注解，例如最常用的@PreAuthorize
 * configure(HttpSecurity): Request层面的配置，对应XML Configuration中的<http>元素
 * configure(WebSecurity): Web层面的配置，一般用来配置无需安全检查的路径
 * configure(AuthenticationManagerBuilder): 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static Logger _log = LoggerFactory.getLogger(SecurityConfig.class);


    @Autowired
    private CustomUserService customUserService;

    /**
     * The method name "configureGlobal" can by any string,
     * the important thing is there has only configure AuthenticationManagerBuilder in the @EnableWebSecurity
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // 也可设置provider
        // BCryptPasswordEncoder 强hash加密
        auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
        _log.debug("SecurityConfig init ...");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/assets/**", "/api/login", "/api/logout")
                .permitAll().anyRequest().authenticated()
//              .antMatchers("/admin/**").access("hasRole('ADMIN')")
//              .and().rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400)
                .and().csrf().disable();
//              .and().sessionManagement().invalidSessionUrl("/login").maximumSessions(-1).maxSessionsPreventsLogin(true).sessionRegistry(sessionRegistry())
//              .and().exceptionHandling().accessDeniedPage("/Access_Denied");

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

}
