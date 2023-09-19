package org.example.spring.database.pool;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ConnectionPool {
    private final String username;
    private final Integer size;
    private final List<Object> args;
    private final Map<String, Object> properties;
}
