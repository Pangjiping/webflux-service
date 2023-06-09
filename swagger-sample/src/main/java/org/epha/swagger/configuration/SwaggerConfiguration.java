package org.epha.swagger.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toSet;

/**
 * @author pangjiping
 */
@Slf4j
@Configuration
@EnableSwagger2WebFlux
@RequiredArgsConstructor
public class SwaggerConfiguration {

    private static final String SECURITY_KEY_NAME = "Bearer";

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .genericModelSubstitutes(Mono.class, Flux.class)
                .useDefaultResponseMessages(false)
                .produces(Arrays.asList("application/json;charset=UTF-8").stream().collect(toSet()))
                .securitySchemes(singletonList(securityScheme()))
                .securityContexts(singletonList(securityContext()))
                .ignoredParameterTypes(ServerWebExchange.class, Resource.class, ResponseEntity.class)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        String title = "Example API";
        String description = "Config Spring-boot Reactive Swagger";
        String version = "1.0";
        String termOfServiceUrl = "https://www.jittagornp.me/term-of-service";
        String contactName = "จิตกร พิทักษ์เมธากุล";
        String contactUrl = "https://www.jittagornp.me/contact";
        String contactEmail = "jittagornp@gmail.com";
        String license = "ไม่อนุญาตให้นำส่วนหนึ่งส่วนใดของ API ไปใช้งาน ยกเว้นจะได้รับอนุญาตจากเจ้าของผลงาน";
        String licenseUrl = "https://www.jittagornp.me/license";
        return new ApiInfo(
                title,
                description,
                version,
                termOfServiceUrl,
                new springfox.documentation.service.Contact(contactName, contactUrl, contactEmail),
                license,
                licenseUrl,
                Collections.emptyList()
        );
    }

    private SecurityScheme securityScheme() {
        return new ApiKey(SECURITY_KEY_NAME, "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(path -> {
                    if (path.startsWith("/auth/")) {
                        return false;
                    }
                    if (path.startsWith("/public/")) {
                        return false;
                    }
                    return true;
                })
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        return singletonList(new SecurityReference(SECURITY_KEY_NAME, new AuthorizationScope[]{
                new AuthorizationScope("global", "accessEverything")
        }));
    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder() //<20>
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.MODEL)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.LIST)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .showCommonExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

}
