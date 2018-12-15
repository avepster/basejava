package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ListWithHeaderAndPeriodSection extends AbstractSection {
    List<HeaderAndPeriodData> list = new ArrayList<>();

    public List<HeaderAndPeriodData> getHeaderAndPeriodList() {
        return list;
    }

    public void setHeaderAndPeriodList(List<HeaderAndPeriodData> list) {
        this.list = list;
    }
}
