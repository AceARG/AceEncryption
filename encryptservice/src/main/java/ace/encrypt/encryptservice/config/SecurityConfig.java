package ace.encrypt.encryptservice.config;

import ace.encrypt.encryptservice.service.EncryptionServiceImp;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    private final EncryptionServiceImp encryptionServiceImp;

    public SecurityConfig(EncryptionServiceImp encryptionServiceImp) {
        this.encryptionServiceImp = encryptionServiceImp;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(encryptionServiceImp.encrypt("password")) // Encrypt the raw password
                .roles("USER");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and();
    }
}
