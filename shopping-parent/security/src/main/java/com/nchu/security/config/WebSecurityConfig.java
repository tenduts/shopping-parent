package com.nchu.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder(){
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //对controller做验证
                .antMatchers().permitAll()
                //.anyRequest().authenticated()
                //.and()
                //文件夹验证
                .antMatchers( "/resources/**", "/signup" , "/about").permitAll()
                .antMatchers( "/admin/**").hasRole("ADMIN" )
                .antMatchers( "/user/**").access("hasRole('USER')")
                .anyRequest().authenticated()
                .and()
                //指定登录页面
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout().permitAll();
        http.csrf().disable();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

}
