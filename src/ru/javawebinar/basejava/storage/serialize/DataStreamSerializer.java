package ru.javawebinar.basejava.storage.serialize;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, ElementWriter<T> elementWriter) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            elementWriter.accept(element);
        }
    }

    private <T> List<T> readWithException(DataInputStream dis, ElementReader<T> elementReader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(elementReader.get());
        }
        return list;
    }

    private void setWithException(DataInputStream dis, Setter setter) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            setter.set();
        }
    }

    @Override
    public void doWrite(OutputStream os, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeWithException(contacts.entrySet(), dos, element -> {
                dos.writeUTF(element.getKey().name());
                dos.writeUTF(element.getValue());
            });
            Map<SectionType, AbstractSection> sections = resume.getSections();
            writeWithException(sections.entrySet(), dos, element -> {
                SectionType sectionType = element.getKey();
                AbstractSection sectionValue = element.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) sectionValue).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = ((ListSection) sectionValue).getList();
                        writeWithException(list, dos, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = ((OrganizationSection) sectionValue).getOrganizationList();
                        writeWithException(organizations, dos, organization -> {
                            dos.writeUTF(organization.getHomePage().getName());
                            String url = organization.getHomePage().getUrl();
                            dos.writeUTF(url == null ? "" : url);
                            List<Organization.Position> positions = organization.getPositions();
                            writeWithException(positions, dos, position -> {
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getTitle());
                                String description = position.getDescription();
                                dos.writeUTF(description == null ? "" : description);
                            });

                        });
                        break;
                }
            });
        }
    }

    private AbstractSection readSectionWithException(SectionType sectionType, DataInputStream dis) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListSection(readWithException(dis, dis::readUTF));
            case EXPERIENCE:
            case EDUCATION:
                return new OrganizationSection(readWithException(dis, () -> {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            return new Organization(name, url.equals("") ? null : url,
                                    DataStreamSerializer.this.readWithException(dis, () ->
                                            {
                                                LocalDate startDate = LocalDate.parse(dis.readUTF());
                                                LocalDate endDate = LocalDate.parse(dis.readUTF());
                                                String title = dis.readUTF();
                                                String description = dis.readUTF();
                                                return new Organization.Position(
                                                        startDate, endDate, title,
                                                        description.equals("") ? null : description
                                                );
                                            }
                                    )
                            );
                        }
                )
                );
            default:
                return null;
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            setWithException(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            setWithException(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSectionWithException(sectionType, dis));
            });
            return resume;
        }
    }

    @FunctionalInterface
    private interface ElementWriter<T> {
        void accept(T element) throws IOException;
    }

    @FunctionalInterface
    private interface ElementReader<T> {
        T get() throws IOException;
    }

    @FunctionalInterface
    private interface Setter {
        void set() throws IOException;
    }
}
