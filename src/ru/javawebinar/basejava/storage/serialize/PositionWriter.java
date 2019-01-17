package ru.javawebinar.basejava.storage.serialize;

import ru.javawebinar.basejava.model.Organization;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class PositionWriter implements CollectionWriter {
    @Override
    public void write(Collection collection, DataOutputStream dos) throws IOException {
        List<Organization.Position> positions = (List<Organization.Position>) collection;
        dos.writeInt(positions.size());
        for (int j = 0; j < positions.size(); j++) {
            dos.writeUTF(positions.get(j).getStartDate().toString());
            dos.writeUTF(positions.get(j).getEndDate().toString());
            dos.writeUTF(positions.get(j).getTitle());
            if (positions.get(j).getDescription() == null) {
                dos.writeUTF("");
            } else {
                dos.writeUTF(positions.get(j).getDescription());
            }
        }
    }
}
