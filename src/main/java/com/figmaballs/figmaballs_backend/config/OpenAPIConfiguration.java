package com.figmaballs.figmaballs_backend.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;

@Configuration
public class OpenAPIConfiguration {

    private ServletContext context;

    /**
     * Konstruktor für die OpenAPI-Konfiguration.
     *
     * @param context Der Servlet-Kontext.
     */
    public OpenAPIConfiguration(ServletContext context) {
        this.context = context;
    }

    /**
     * Bean-Methode zur Konfiguration der OpenAPI.
     *
     * @return Die OpenAPI-Konfiguration.
     */
    @Bean
    public OpenAPI springShopOpenAPI(
            //  @Value("${info.app.version}") String appVersion,
    ) {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .addServersItem(new Server().url(this.context.getContextPath()))
                .info(new Info()
                        .title("Project management service")
                        .description("Moin meister \nLigma balls ( ͡° ͜ʖ ͡°)")

                        .version("0.1"))
                /*.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )*/;
    }


}