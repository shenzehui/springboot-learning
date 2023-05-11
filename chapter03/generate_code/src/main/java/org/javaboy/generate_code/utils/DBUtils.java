package org.javaboy.generate_code.utils;

import org.javaboy.generate_code.model.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author szh
 */
public class DBUtils {
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static Connection initDb(Db db) {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }

}
