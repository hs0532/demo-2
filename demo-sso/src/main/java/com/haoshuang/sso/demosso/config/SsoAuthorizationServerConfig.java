package com.haoshuang.sso.demosso.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    private RedisTokenStore redisTokenStore;



    /**
     * JwtTokenStore
     *
     * @return
     */
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 生成JTW token
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("andy");
        return converter;
    }

    @Bean
    public ClientDetailsService clientDetails() {
        JdbcClientDetailsService service = new JdbcClientDetailsService(dataSource);
        return service;
    }

        @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      /*  clients.inMemory().
                withClient("ccc")//客户端ID
                .secret("{noop}123456")//客户端密码
                .authorizedGrantTypes("authorization_code","refresh_token")//认证方式 授权码认证模式
                .scopes("all")
                .autoApprove(true)
                .and()
                .withClient("bbb")
                .secret("{noop}123456")
                .authorizedGrantTypes("authorization_code","refresh_token")
                .scopes("all");*/

        //数据库存储客户端信息
         clients.withClientDetails(clientDetails());

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtTokenStore())
                .accessTokenConverter(jwtAccessTokenConverter());

    }
}
