package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Position {
    private final LocalDate dateBegin;
    private final LocalDate dateEnd;
    private final String title;
    private final String description;

    public Position(LocalDate dateBegin, LocalDate dateEnd, String title, String description) {
        Objects.requireNonNull(dateBegin, "dateBegin must not be null");
        Objects.requireNonNull(dateEnd, "dateEnd must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.title = title;
        this.description = description;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (!dateBegin.equals(position.dateBegin)) return false;
        if (!dateEnd.equals(position.dateEnd)) return false;
        if (!title.equals(position.title)) return false;
        return description != null ? description.equals(position.description) : position.description == null;
    }

    @Override
    public int hashCode() {
        int result = dateBegin.hashCode();
        result = 31 * result + dateEnd.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
