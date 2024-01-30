package GLSIB_GROUPE5.example.EgaSApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "EGA_Banking",
				version = "1.0.0",
				description = "Une application de digital banking destinée à l'entreprise Ega",
				contact = @Contact(
						name = "s-Thierno",
						email = "davidouthe2@gmail.com"
				)
		)
)
public class EgaSApplication {

	public static void main(String[] args) {
		SpringApplication.run(EgaSApplication.class, args);
	}

}
