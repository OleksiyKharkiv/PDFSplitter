package com.example.pdfsplitterbackend.enums;

public enum SplitMode {
    SEPARATE_PAGES(0),
    SPLIT_IN_HALF(1),
    GROUP_BY_PAGE_COUNT(2),
    SPLIT_BY_SIZE(3),
    SPLIT_BY_PAGES_RANGE(4);
    private int value;
SplitMode(int value){this.value = value;}

    public int getValue() {
        return value;
    }
}