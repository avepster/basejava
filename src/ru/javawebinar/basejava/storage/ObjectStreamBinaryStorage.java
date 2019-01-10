package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Serialize.SerializeStrategy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ObjectStreamBinaryStorage extends AbstractFileStorage {
    private static SerializeStrategy strategy;

    protected ObjectStreamBinaryStorage(File directory, SerializeStrategy strategy) {
        super(directory);
        this.strategy = strategy;
    }

    @Override
    public void doWrite(OutputStream os, Resume resume) throws IOException {
        strategy.doWrite(os, resume);
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        return strategy.doRead(is);
    }
}
