package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected abstract void doWrite(OutputStream os, Resume resume) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;

    protected AbstractPathStorage(String dir) {
        Path directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must be not null");
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(directory.toFile().getAbsolutePath() + " is not directory");
        }
        if (!directory.toFile().canRead() || !directory.toFile().canWrite()) {
            throw new IllegalArgumentException(directory.toFile().getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return new File(directory.toFile(), uuid).toPath();
    }

    @Override
    protected void doSave(Path path, Resume resume) {
        try {
            path.toFile().createNewFile();
        } catch (IOException e) {
            throw new StorageException("Can't create Path " + path.toFile().getAbsolutePath(), path.toFile().getName(), e);
        }
        doUpdate(path, resume);
    }

    @Override
    protected void doUpdate(Path path, Resume resume) {
        try {
            doWrite(new BufferedOutputStream(new FileOutputStream(path.toFile())), resume);
        } catch (IOException e) {
            throw new StorageException("Path write error", path.toFile().getName(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        if (!path.toFile().delete()) {
            throw new StorageException("Path delete error", path.toFile().getName());
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("IO Exception", path.toFile().getName(), e);
        }
    }

    @Override
    protected List<Resume> getAll() {
        File[] fileList = directory.toFile().listFiles();
        if (fileList == null) {
            throw new StorageException("Directory read error", directory.toFile().getName(), null);
        }
        List<Resume> resumeList = new ArrayList<>();
        for (File file : fileList) {
            resumeList.add(doGet(file.toPath()));
        }
        return resumeList;
    }

    @Override
    protected boolean isExist(Path path) {
        return path.toFile().exists();
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }

    @Override
    public int size() {
        String[] list = directory.toFile().list();
        if (list == null) {
            throw new StorageException("Directory read error", null);
        }
        return list.length;
    }
}
