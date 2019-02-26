package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;
import ru.javawebinar.basejava.sql.SQLHelper;

import java.sql.*;
import java.util.List;

public class SQLStorage implements Storage {
    private static ConnectionFactory connectionFactory;
    private static SQLHelper sqlHelper;

    public SQLStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        sqlHelper = new SQLHelper(connectionFactory);
    }

    @Override
    public void save(Resume resume) {
        int numRows = sqlHelper.execUpdate("INSERT INTO resume (uuid, full_name) values(?, ?)", resume.getUuid(), resume.getFullName());
        if (numRows == 0) {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void update(Resume resume) {
        int numRows = sqlHelper.execUpdate("UPDATE resume SET full_name = ? WHERE uuid = ?", resume.getFullName(), resume.getUuid());
        if (numRows == 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void delete(String uuid) {
        int numRows = sqlHelper.execUpdate("DELETE FROM resume r WHERE r.uuid = ?", uuid);
        if (numRows == 0) {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void clear() {
        sqlHelper.execUpdate("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
/*        ResultSet rs = sqlHelper.execQuery("SELECT r.* FROM resume r WHERE r.uuid = ?", uuid);
        Resume r;
        try {
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            r = new Resume(uuid, rs.getString("full_name"));
        } catch (SQLException e) {
            throw new StorageException(e);
        }
        return r;*/

        Resume r;
        try (Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT r.* FROM resume r WHERE r.uuid = ?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            r = new Resume(uuid, rs.getString("full_name"));
        } catch (SQLException e) {
            throw new StorageException(e);
        }
        return r;
    }

    @Override
    public List<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
