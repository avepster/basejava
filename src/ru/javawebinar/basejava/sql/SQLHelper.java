package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageException;

import java.sql.*;

public class SQLHelper {
    private static ConnectionFactory connectionFactory;

    public SQLHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public static boolean execSql(String sql, String... params) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            int i = 1;
            for (String param : params) {
                ps.setString(i, param);
                i++;
            }
            return ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public static ResultSet execQuery(String sql, String... params) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (String param : params) {
                int i = 0;
                i++;
                ps.setString(i, param);
            }
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}