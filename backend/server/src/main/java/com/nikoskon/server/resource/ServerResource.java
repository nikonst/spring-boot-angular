package com.nikoskon.server.resource;

import com.nikoskon.server.enumeration.Status;
import com.nikoskon.server.model.Response;
import com.nikoskon.server.model.Server;
import com.nikoskon.server.service.implementation.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import static java.util.Optional.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerServiceImpl serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder().timeStamp(LocalDateTime.now()).
                        data(Map.of("servers", serverService.list(30))).
                        message("Servers retrieved").
                        status(OK).
                        statusCode(OK.value()).build()

        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder().timeStamp(LocalDateTime.now()).
                        data(Map.of("server", server).
                        message(server.getStatus() == Status.SERVER_UP ? "Ping Success" : "Ping Fail").
                        status(OK).
                        statusCode(OK.value()).build()

        );
    }
}
