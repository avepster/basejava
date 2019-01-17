package ru.javawebinar.basejava.storage.serialize;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;

@FunctionalInterface
public interface CollectionWriter {
    void write(Collection collection, DataOutputStream dos) throws IOException;
}
