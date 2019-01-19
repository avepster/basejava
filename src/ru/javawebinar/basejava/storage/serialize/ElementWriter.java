package ru.javawebinar.basejava.storage.serialize;

import java.io.IOException;

@FunctionalInterface
public interface ElementWriter<T> {
    void accept(T element) throws IOException;
}
