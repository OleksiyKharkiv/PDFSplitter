package com.example.pdfsplitterbackend.enums;

public enum RequestStatus {
    IN_QUEUE(0),
    IN_PROGRESS(1),
    COMPLETED(2);
    private final int value;

    RequestStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}