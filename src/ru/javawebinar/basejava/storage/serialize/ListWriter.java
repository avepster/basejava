package ru.javawebinar.basejava.storage.serialize;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class ListWriter implements CollectionWriter {
    @Override
    public void write(Collection collection, DataOutputStream dos) throws IOException {
        int sizeList = collection.size();
        dos.writeInt(sizeList);
        for (int i = 0; i < sizeList; i++) {
            dos.writeUTF((String) ((List) collection).get(i));
        }
    }
}
