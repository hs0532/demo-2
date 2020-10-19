package com.haoshuang.sso.demosso.config;

import com.haoshuang.sso.demosso.config.ValidateCode.ValidateCodeFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyFailAuthenticationFailHandler myFailAuthenticationFailHandler;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    MyCusUserServiceDetail userServiceDetail;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin() //设置表单登录
                .loginPage("/login")//设置登录跳转页面controller、也可以直接跳转页面
                .loginProcessingUrl("/authentication/form") //自定义登录页面的表单提交地址
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myFailAuthenticationFailHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/login","/register","*/css/**","/code/image","/logout","/code/sms").permitAll()//过滤不需要拦截认证的资源
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();

        http.logout().permitAll()
                .logoutUrl("/logout")//注销
                .logoutSuccessUrl("/login");//注销成功后跳转的页面

     /* http.cors().and().csrf().disable()
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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceDetail).passwordEncoder(new BCryptPasswordEncoder());
    }
}
