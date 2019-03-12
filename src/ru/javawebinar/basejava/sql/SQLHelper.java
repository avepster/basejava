package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLHelper {
    private final ConnectionFactory connectionFactory;

    public SQLHelper(String dbUrl, String dbUser, String dbPassword) {
        this.connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public interface ExecutionProcess<T> {
        T execute(PreparedStatement ps) throws SQLException;
    }

    public <T> T sqlExecute(String sql, ExecutionProcess<T> executionProcess) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return executionProcess.execute(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

}