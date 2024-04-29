package com.example.work_liuchangxu.work_0429;

import java.util.List;

public class MyData<T> {

    private final List<T> records;

    private final int total;

    private final int size;

    private final int current;

    private final boolean searchCount;

    private final int pages;

    public MyData(List<T> records, int total, int size, int current, boolean searchCount, int pages) {
        this.records = records;
        this.total = total;
        this.size = size;
        this.current = current;
        this.searchCount = searchCount;
        this.pages = pages;
    }

    public List<T> getRecords() {
        return records;
    }

    public int getCurrent() {
        return current;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public int getPages() {
        return pages;
    }

    public int getSize() {
        return size;
    }

    public int getTotal() {
        return total;
    }

}
