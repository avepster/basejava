package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private final ArrayList<Position> positions;

    public Organization(String name, String url, ArrayList<Position> positions) {
        Objects.requireNonNull(positions, "positions must not be null");
        this.homePage = new Link(name, url);
        this.positions = positions;
    }

    public Link getHomePage() {
        return homePage;
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        return positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + positions.hashCode();
        return result;
    }
}