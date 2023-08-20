package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLData {

private static QueryRunner queryRunner;
private static Connection connection;

    @SneakyThrows
    private static void getConn() {
        queryRunner = new QueryRunner();
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "app", "pass");
    }

    @SneakyThrows
    public static String getStatusOfPayment() {
        String codeSQL = "SELECT status FROM payment_entity";
        getConn();
        return queryRunner.query(connection, codeSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void cleanDatabase(){
        getConn();
        queryRunner.update(connection, "DELETE FROM payment_entity");
    }

}
