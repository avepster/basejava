package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ListWithHeaderAndPeriodSection extends AbstractSection {
    List<HeaderAndPeriodData> complexList = new ArrayList<>();

    public List<HeaderAndPeriodData> getComplexList() {
        return complexList;
    }

    public void setComplexList(List<HeaderAndPeriodData> complexList) {
        this.complexList = complexList;
    }
}
