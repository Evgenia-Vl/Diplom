package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLData {

private static QueryRunner queryRunner;
private static Connection connection;

    private static final String dbUsername = System.getProperty("db.Username");
    private static final String dbPassword = System.getProperty("db.Password");
    private static final String dbUrl = System.getProperty("db.url");

       @SneakyThrows
    private static void getConn() {
        queryRunner = new QueryRunner();
           connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    @SneakyThrows
    public static String getStatusOfPayment() {
        String codeSQL = "SELECT status FROM payment_entity ORDER by created DESC LIMIT 1";
        getConn();
        return queryRunner.query(connection, codeSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getStatusOfCreditPayment() {
        var codeSQL = "SELECT status FROM credit_request_entity ORDER by created DESC LIMIT 1";
        getConn();
        return queryRunner.query(connection, codeSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void cleanDatabase(){
           getConn();
           var payments = "DELETE FROM payment_entity";
           var creditPayments = "DELETE FROM credit_request_entity";
           var orders = "DELETE FROM order_entity";
        queryRunner.execute(connection, payments);
        queryRunner.execute(connection, creditPayments);
        queryRunner.execute(connection, orders);
    }

    }
