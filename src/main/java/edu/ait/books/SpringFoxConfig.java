package edu.ait.books;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
    public static final Contact CUSTOM_CONTACT = new Contact("Kieran Whooley", "www.aitlit.ie", "student@student.mail.ie");
    public static final ApiInfo CUSTOM_API_INFO = new ApiInfoBuilder().title("Books Service")
            .description("A list of Books")
            .version("1.0")
            .contact(CUSTOM_CONTACT)
            .build();

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("edu.ait.books"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(CUSTOM_API_INFO);
    }
}
