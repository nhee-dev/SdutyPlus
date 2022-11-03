package com.d205.sdutyplus.util;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Server;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

//http://localhost:8090/swagger-ui/index.html#/
@OpenAPIDefinition(
        info = @Info(title = "API 명세서",
                description = "API 명세서 테스트 입니다.",
                version = "v1"))
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public Docket api() {
        final ApiInfo apiInfo = new ApiInfoBuilder()
                .title("구미 자율2반 5팀")
                .description("<h3>김남희 김정윤 배시현 배한용 서재형 편예린</h3>")
                .contact(new Contact("D205", "https://edu.ssafy.com", "ssafy@ssafy.com"))
                .license("MIT License")
                .version("1.0")
                .build();

        Server serverLocal = new Server("local", "http://localhost:8090", "for local usages", Collections.emptyList(), Collections.emptyList());
        Server testServer = new Server("test", "https://d205.kro.kr/api", "for testing", Collections.emptyList(), Collections.emptyList());
        Server testServer2 = new Server("test2", "http://k7d205.p.ssafy.io:8081", "for testing", Collections.emptyList(), Collections.emptyList());

        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo)
                .servers(serverLocal, testServer, testServer2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.d205.domain.**"))
                .paths(PathSelectors.ant("/**"))
                .build();
    }

//    private ApiKey apiKey() {
//        return new ApiKey(JwtProperties.JWT_ACCESS_NAME, JwtProperties.JWT_ACCESS_NAME, "header");
//    }

}
