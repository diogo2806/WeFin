package br.com.wefin.GestaoEmprestimosEmpresaX.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // Indica que esta classe é uma classe de configuração do Spring
public class WebMvcConfig implements WebMvcConfigurer {

    // Configura o comportamento de correspondência de caminho
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // Utiliza o método setUseTrailingSlashMatch para obter um comportamento semelhante
        configurer.setUseTrailingSlashMatch(false);
    }

    // Configura as políticas de CORS (Cross-Origin Resource Sharing)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Aplica as configurações de CORS para todos os endpoints sob '/api/**'
        registry.addMapping("/api/**")
            // Permite qualquer origem para acessar os recursos
            .allowedOrigins("*")
            // Permite os métodos HTTP especificados
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            // Permite todos os cabeçalhos
            .allowedHeaders("*");
    }
}
