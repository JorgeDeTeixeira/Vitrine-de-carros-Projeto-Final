package vitrine.marca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MarcaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarcaApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("senha123"));
	}

}
