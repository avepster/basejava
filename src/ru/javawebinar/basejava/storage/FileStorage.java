package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class FileStorage extends AbstractFileStorage {

    protected FileStorage(File directory) {
        super(directory);
    }

    @Override
    protected Resume doRead(File file) {
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Resume) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error", e);
        }
    }

    @Override
    protected void doWrite(File file, Resume resume) {
        try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(resume);
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
    }
}
