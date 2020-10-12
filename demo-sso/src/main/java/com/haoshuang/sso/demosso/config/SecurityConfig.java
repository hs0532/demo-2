package com.haoshuang.sso.demosso.config;

import com.haoshuang.sso.demosso.config.filter.JWTAuthenticationFilter;
import com.haoshuang.sso.demosso.config.filter.JWTAuthorizationFilter;
import com.haoshuang.sso.demosso.service.MyCusUserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyCusUserServiceDetail userServiceDetail;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//             .and()
//        // 不需要session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/demo/**").hasRole("ADMIN")
                // 测试用资源，需要验证了的用户才能访问
                .antMatchers("/demo/**").authenticated()
                // 其他都放行了
                .anyRequest().permitAll()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceDetail).passwordEncoder(new BCryptPasswordEncoder());
    }
}
