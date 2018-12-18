package ru.javawebinar.basejava.model;

import java.util.List;

public class OrganizationSection extends AbstractSection {
    private List<Position> list;

    public OrganizationSection(List<Position> list) {
        this.list = list;
    }

    public List<Position> getOrganizationList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return list != null ? list.equals(that.list) : that.list == null;
    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }
}