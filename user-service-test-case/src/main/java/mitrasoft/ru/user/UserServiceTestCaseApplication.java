package mitrasoft.ru.user;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@OpenAPIDefinition(
		info = @Info(
				title = "UserService",
				version = "1.0",
				description = "Provides an API to interact with users"
		)
)
@SpringBootApplication
@EnableR2dbcRepositories
public class UserServiceTestCaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceTestCaseApplication.class, args);
	}

}
