package com.epam.trainings.testauto.jdbc;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Delegate;

import java.io.Closeable;
import java.sql.*;
import java.util.function.Consumer;

@AllArgsConstructor
public class PooledConnection implements Connection {

    @Delegate(excludes = Closeable.class)
    private Connection connection;
    private Consumer<PooledConnection> connectionConsumer;

    @Override
    public void close() {
        connectionConsumer.accept(this);
    }

    @SneakyThrows
    public void reallyClose() {
        connection.close();
    }
}
