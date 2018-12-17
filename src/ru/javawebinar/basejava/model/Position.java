package ru.javawebinar.basejava.model;

import java.time.LocalDate;

public class Position {
    private String header;
    private String link;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private String text;

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

    public Position(String header, String link, LocalDate dateBegin, LocalDate dateEnd, String text) {
        this.header = header;
        this.link = link;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.text = text;
    }
}