package com.nikoskon.server;

import com.nikoskon.server.enumeration.Status;
import com.nikoskon.server.model.Server;
import com.nikoskon.server.model.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(null, "192.168.116.2",
					"Ubuntu Linux", "16 GB", "PC",
					"http://localhost:8080/server/image/server1.png",
					Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.116.25",
					"Fedora Linux", "16 GB", "PC",
					"http://localhost:8080/server/image/server2.png",
					Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.116.29",
					"Red Hat Linux", "16 GB", "PC",
					"http://localhost:8080/server/image/server3.png",
					Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.116.33",
					"Zorin Linux", "16 GB", "PC",
					"http://localhost:8080/server/image/server4.png",
					Status.SERVER_UP));
		};
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Origin", "Accept", "Jwt-Token",
				"Access-Control-Allow-Origin", "Authorization", "content-type", "x-auth-token"));
		configuration.setExposedHeaders(Arrays.asList("x-auth-token","Origin", "Accept",
				"Access-Control-Allow-Origin", "Jwt-Token", "filename" ));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
