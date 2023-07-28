package com.nikoskon.server;

import com.nikoskon.server.enumeration.Status;
import com.nikoskon.server.model.Server;
import com.nikoskon.server.model.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
					"htp://localhost:8080/server/image/server1.png",
					Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.116.25",
					"Fedora Linux", "16 GB", "PC",
					"htp://localhost:8080/server/image/server2.png",
					Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.116.29",
					"Red Hat Linux", "16 GB", "PC",
					"htp://localhost:8080/server/image/server3.png",
					Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.116.33",
					"Zorin Linux", "16 GB", "PC",
					"htp://localhost:8080/server/image/server4.png",
					Status.SERVER_UP));
		};
	}
}
