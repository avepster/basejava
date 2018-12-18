package ru.javawebinar.basejava.model;

import java.time.LocalDate;

public class Position {
    private String header;
    private String link;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private String text;

    public Position(String header, String link, LocalDate dateBegin, LocalDate dateEnd, String text) {
        this.header = header;
        this.link = link;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.text = text;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (!header.equals(position.header)) return false;
        if (link != null ? !link.equals(position.link) : position.link != null) return false;
        if (!dateBegin.equals(position.dateBegin)) return false;
        if (!dateEnd.equals(position.dateEnd)) return false;
        return text != null ? text.equals(position.text) : position.text == null;
    }

    @Override
    public int hashCode() {
        int result = header.hashCode();
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + dateBegin.hashCode();
        result = 31 * result + dateEnd.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}