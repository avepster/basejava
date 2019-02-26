package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLHelper {
    private static ConnectionFactory connectionFactory;

    public SQLHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public static boolean execSql(String sql, String... params) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            setParams(ps, params);
            return ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public static int execUpdate(String sql, String... params) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            setParams(ps, params);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public static ResultSet execQuery(String sql, String... params) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            setParams(ps, params);
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    private static void setParams(PreparedStatement ps, String... params) {
        try {
            int i = 1;
            for (String param : params) {
                ps.setString(i, param);
                i++;
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}