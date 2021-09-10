package tk.mwacha.configuration;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import tk.mwacha.entities.User;
import tk.mwacha.service.UserService;


@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfig {

    @Configuration
    @EnableResourceServer
    @RequiredArgsConstructor
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


        public final TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.tokenStore(tokenStore);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .and().authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .anyRequest().fullyAuthenticated();

        }

    }

    @Configuration
    @EnableAuthorizationServer
    @RequiredArgsConstructor
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


        @Qualifier("authenticationManagerBean")
        private final AuthenticationManager authenticationManager;


        private final CustomUserDetailsService userDetailsService;


        private final PasswordEncoder passwordEncoder;

        private final DataSource dataSource;

        private final UserService userService;

        @Bean
        public TokenStore tokenStore() {
            return new CustomJdbcTokenStore(dataSource);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .tokenStore(tokenStore())
                    .authenticationManager(this.authenticationManager)
                    .tokenEnhancer(accessTokenConverter())
                    .userDetailsService(userDetailsService);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setTokenStore(tokenStore());
            return tokenServices;
        }

        @Bean
        public JwtAccessTokenConverter accessTokenConverter() {
            JwtAccessTokenConverter converter = new CustomTokenEnhancer();
            return converter;
        }

        class CustomTokenEnhancer extends JwtAccessTokenConverter {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                String email = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();

                Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());

                User user = userService.findByEmail(email);

                info.put("id", user.getId());

                info.put("email", user.getEmail());

                DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
                customAccessToken.setAdditionalInformation(info);

                return super.enhance(customAccessToken, authentication);
            }
        }

    }

}