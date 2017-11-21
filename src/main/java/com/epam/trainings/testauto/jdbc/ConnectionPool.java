package com.epam.trainings.testauto.jdbc;

import lombok.SneakyThrows;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public class ConnectionPool implements JdbcDao, Closeable {

    private BlockingQueue<PooledConnection> connectionBlockingQueue;

    private volatile boolean isClosed;

    public ConnectionPool(String resourceName, String sqlInitResourceName) {
        this(resourceName);
        executeSql("/" + sqlInitResourceName + ".sql");
    }

    @SneakyThrows
    public ConnectionPool(String resourceName) {

        Properties properties = new Properties();
        properties.load(
                ConnectionPool.class.getResourceAsStream("/" + resourceName + ".properties"));

        Class.forName((String) properties.remove("driver"));

        String url = (String) properties.remove("url");
        int poolSize = Integer.parseInt((String)
                properties.remove("poolSize"));
        connectionBlockingQueue = new ArrayBlockingQueue<>(poolSize);

        assert properties.size() == 2;
        assert properties.containsKey("user");
        assert properties.containsKey("password");

        Consumer<PooledConnection> pooledConnectionConsumer = e -> {
            if (isClosed)
                e.reallyClose();
            else
                connectionBlockingQueue.offer(e);
        };

        for (int i = 0; i < poolSize; i++)
            connectionBlockingQueue.offer(
                    new PooledConnection(
                            DriverManager.getConnection(url, properties),
                            pooledConnectionConsumer));
    }

    @Override
    public Connection checkedGet() throws Throwable {
        if (isClosed)
            throw new IOException();
        return connectionBlockingQueue.take();
    }

    @Override
    public void close() {
        isClosed = true;
        for (PooledConnection pooledConnection : connectionBlockingQueue)
            pooledConnection.reallyClose();
    }
}

