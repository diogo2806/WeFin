package br.com.wefin.GestaoEmprestimosEmpresaX.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration  // Indica que esta classe é uma classe de configuração do Spring
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Sobrescreve o método para configurar as definições de segurança
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Configura as políticas de segurança HTTP
        http
            // Desativa a proteção CSRF (Cross-Site Request Forgery)
            .csrf(csrf -> csrf.disable())
            // Configura as autorizações de requisição
            .authorizeRequests(requests -> 
                // Permite todas as requisições, independente da origem ou autenticação
                requests.anyRequest().permitAll()
            );
    }
}
