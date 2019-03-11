package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageException;

import java.sql.*;

public class SQLHelper {
    private static ConnectionFactory connectionFactory;

    public SQLHelper(String dbUrl, String dbUser, String dbPassword) {
        this.connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public interface ExecutionProcess {
        Object execute(PreparedStatement ps) throws SQLException;
    }

    public Object sqlExecute(String sql, ExecutionProcess executionProcess) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return executionProcess.execute(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

}