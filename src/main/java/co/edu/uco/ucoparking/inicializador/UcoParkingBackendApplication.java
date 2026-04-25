package co.edu.uco.ucoparking.inicializador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //Anotacion que indica que esta clase es la clase principal de la aplicación Spring Boot
public class UcoParkingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(UcoParkingBackendApplication.class, args);
	}

}
