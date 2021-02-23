package com.irrt.hibernate.one_to_many.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
        String user = "one-to-one-uni";
        String pass = "one-to-one-uni";

        try {
            System.out.println("Connecting to database" + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection successful" );

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
