package ru.javawebinar.basejava.storage.serialize;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(OutputStream os, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            // TODO implements section
            Map<SectionType, AbstractSection> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                switch (entry.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(entry.getKey().toString());
                        dos.writeUTF(entry.getValue().toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        dos.writeUTF(entry.getKey().toString());
                        int sizeList = ((ListSection) entry.getValue()).getList().size();
                        dos.writeInt(sizeList);
                        for (int i = 0; i < sizeList; i++) {
                            dos.writeUTF(((ListSection) entry.getValue()).getList().get(i));
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        dos.writeUTF(entry.getKey().toString());
                        OrganizationSection orgSection = (OrganizationSection) entry.getValue();
                        int sizeOrgs = orgSection.getOrganizationList().size();
                        dos.writeInt(sizeOrgs);
                        Organization org;
                        for (int i = 0; i < sizeOrgs; i++) {
                            org = orgSection.getOrganizationList().get(i);
                            dos.writeUTF(org.getHomePage().getName());
                            if (org.getHomePage().getUrl() == null) {
                                dos.writeUTF("-=xXx=-");
                            } else {
                                dos.writeUTF(org.getHomePage().getUrl());
                            }
                            dos.writeInt(org.getPositions().size());
                            for (int j = 0; j < org.getPositions().size(); j++) {
                                dos.writeUTF(org.getPositions().get(j).getStartDate().toString());
                                dos.writeUTF(org.getPositions().get(j).getEndDate().toString());
                                dos.writeUTF(org.getPositions().get(j).getTitle());
                                if (org.getPositions().get(j).getDescription() == null) {
                                    dos.writeUTF("-=xXx=-");
                                } else {
                                    dos.writeUTF(org.getPositions().get(j).getDescription());
                                }
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            size = dis.readInt();
            SectionType sectionType;
            for (int i = 0; i < size; i++) {
                sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List list = new ArrayList();
                        int sizeList = dis.readInt();
                        for (int l = 0; l < sizeList; l++) {
                            list.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> listOrganizations = new ArrayList<>();
                        String name;
                        String url;
                        Link link;
                        int sizeOrgList = dis.readInt();
                        for (int j = 0; j < sizeOrgList; j++) {
                            name = dis.readUTF();
                            url = dis.readUTF();
                            if (url.equals("-=xXx=-")) {
                                link = new Link(name, null);
                            } else {
                                link = new Link(name, url);
                            }
                            int sizePositions = dis.readInt();
                            List<Organization.Position> positions = new ArrayList<>();
                            LocalDate startDate;
                            LocalDate endDate;
                            String title;
                            String description;
                            for (int k = 0; k < sizePositions; k++) {
                                startDate = LocalDate.parse(dis.readUTF());
                                endDate = LocalDate.parse(dis.readUTF());
                                title = dis.readUTF();
                                description = dis.readUTF();
                                if (description.equals("-=xXx=-")) {
                                    positions.add(new Organization.Position(startDate, endDate, title, null));
                                } else {
                                    positions.add(new Organization.Position(startDate, endDate, title, description));
                                }
                            }
                            listOrganizations.add(new Organization(link, positions));
                        }
                        resume.addSection(sectionType, new OrganizationSection(listOrganizations));
                        break;
                }
            }
            return resume;
        }
    }
}
