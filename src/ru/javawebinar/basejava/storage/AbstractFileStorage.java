package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must be not null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO Exception", file.getName(), e);
        }
    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;

    @Override
    protected void doUpdate(File file, Resume resume) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO Exception", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("Can not delete file", file.getName());
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("IO Exception", file.getName(), e);
        }
    }

    @Override
    protected List<Resume> getAll() {
        File[] fileList = directory.listFiles();
        if (fileList == null) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is empty");
        }
        List<Resume> resumeList = new ArrayList<>();
        for (int i = 0; i < fileList.length; i++) {
            resumeList.add(doGet(fileList[i]));
        }
        return resumeList;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public void clear() {
        File[] fileList = directory.listFiles();
        if (fileList == null) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is empty");
        }
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                if (!fileList[i].delete()) {
                    throw new StorageException("Can not delete file", fileList[i].getName());
                }
            }
        }

    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.list(), directory.getAbsolutePath() + " is empty").length;
    }
}
