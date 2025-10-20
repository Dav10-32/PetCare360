package eci.edu.dosw.planRefuerzo.petCare360;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "eci.edu.dosw.planRefuerzo")
public class PetCareBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetCareBackApplication.class, args);
	}
}
