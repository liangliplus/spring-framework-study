package com.example.securingweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

/**
 * 安全配置
 *
 * @author liangli
 * @Date: 2020/9/13 20:47
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
    /**
     * 定义哪些url 需要被保护，
     * / 和 /home 不需要任何身份认值，所有其他路劲必须要
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/homne").permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        http.addFilterBefore(new BeforeFilter(), UsernamePasswordAuthenticationFilter.class);

        http.addFilterAfter(new AfterCsrfFilter(), CsrfFilter.class);

    }

    /**
     * AuthenticationManagerBuilder 用于创建一个 AuthenticationManager，
     * 让我能够轻松的实现内存验证、LADP验证、
     * 基于JDBC的验证、
     * 添加UserDetailsService、添加AuthenticationProvider。
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
//        .and().passwordEncoder(NoOpPasswordEncoder.getInstance());

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().withUser("user").password(encoder.encode("password")).roles("USER");

    }




}
