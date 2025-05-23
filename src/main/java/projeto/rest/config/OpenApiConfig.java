package projeto.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class OpenApiConfig {
		
		@Bean
		public OpenAPI custonOpenApi(){
			return new OpenAPI()
					.info(new Info()
						.title("Projeto Jdev Restfull JPA PostegreSQL ")
						.version("v1")
						.description("Rumo ao Topo")
						.termsOfService("https://github.com/carlosrafaeleite/Projeto-Jdev-Restfull")
						.license(
							new License()
								.name("Apache 2.0")
								.url("https://github.com/carlosrafaeleite/Projeto-Jdev-Restfull")
							)
						);	
		}

	}
	

