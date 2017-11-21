package com.epam.trainings.testauto.jdbc;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class HWJdbcTest {

    public static final String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @SneakyThrows
    private static void executeSql(String sql) {
        try (Connection con = DriverManager.getConnection(URL, "", "");
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        }
    }

    private static String fromFile(String fileName) {
        try (val scanner = new Scanner(HWJdbc.class.getResourceAsStream("/" + fileName))
                .useDelimiter(System.lineSeparator());
             Stream<String> lines = StreamSupport.stream(
                     Spliterators.spliteratorUnknownSize(scanner, Spliterator.ORDERED),
                     false)) {

            return lines.collect(Collectors.joining());
        }
    }

    @Test
    @SneakyThrows
    public void name() {

        JdbcDao jdbcDao = new ConnectionPool(
                "jdbc",
                "h2");

        jdbcDao.withResultSet("SELECT first_name, last_name FROM Person",
                rs -> {
                    while (rs.next())
                        System.out.printf("Hi! %s %s%n",
                                rs.getString("first_name"),
                                rs.getString("last_name"));
                });
}
}