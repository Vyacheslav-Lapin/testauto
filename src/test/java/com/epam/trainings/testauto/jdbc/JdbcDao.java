package com.epam.trainings.testauto.jdbc;

import lombok.val;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@FunctionalInterface
public interface JdbcDao extends CheckedSupplier<Connection> {

    default <T> T mapConnection(CheckedFunction<Connection, T> connectionMapper) {
        try (Connection con = get()) {
            return connectionMapper.apply(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    default void withConnection(CheckedConsumer<Connection> connectionConsumer) {
        try (Connection con = get()) {
            connectionConsumer.accept(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    default <T> T mapStatement(CheckedFunction<Statement, T> statementMapper) {
        return mapConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                return statementMapper.apply(statement);
            }
        });
    }

    default void withStatement(CheckedConsumer<Statement> statementConsumer) {
        CheckedConsumer<Connection> connectionCheckedConsumer = connection -> {
            try (Statement statement = connection.createStatement()) {
                statementConsumer.accept(statement);
            }
        };
        withConnection(connectionCheckedConsumer);
    }

    default <T> T mapResultSet(String sql,
                               CheckedFunction<ResultSet, T> resultSetMapper) {
        return mapStatement(statement -> {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                return resultSetMapper.apply(resultSet);
            }
        });
    }

    default void withResultSet(String sql,
                               CheckedConsumer<ResultSet> resultSetConsumer) {
        withStatement(statement -> {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                resultSetConsumer.accept(resultSet);
            }
        });
    }
}
