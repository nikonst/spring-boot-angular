package com.nikoskon.server.service;

import com.nikoskon.server.model.Server;

import java.util.Collection;

public interface ServerService {
    Server create(Server server);
    Server ping(String ipAddress);
    Collection<Server> list(int limit);
    Server get(long id);
    Server update(Server server);
    Boolean delete(long id);
}
