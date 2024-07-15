package com.tuempresa.aplicacion.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebMvcConfigurer : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(" http://localhost:5173/") // Reemplazar con la URL de tu frontend
            .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
            .allowedHeaders("*") // Todos los encabezados permitidos
            .allowCredentials(true) // Permitir credenciales (cookies, tokens)
    }
}
