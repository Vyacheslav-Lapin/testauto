package com.epam.trainings.testauto.jdbc;

import lombok.SneakyThrows;
import org.junit.Test;

public class HWJdbcTest {

    @Test
    @SneakyThrows
    public void name() {

        JdbcDao jdbcDao = new ConnectionPool(
                "jdbc",
                "h2");

        jdbcDao.withResultSet(
                "SELECT first_name, last_name FROM Person",
                rs -> {
                    while (rs.next())
                        System.out.printf("Hi! %s %s%n",
                                rs.getString("first_name"),
                                rs.getString("last_name"));
                });
    }
}