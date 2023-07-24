package com.nikoskon.server.model.repo;

import com.nikoskon.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServeRepo extends JpaRepository<Server, Long> {
    Server findByIPAddress(String ipAddress);
    //Server findByName(String name);
}
