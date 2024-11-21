package com.example.cinematicket.enums;


import lombok.Getter;

@Getter
public enum ShowTimeStatus {
    SCHEDULED(1),
    ONGOING(2),
    COMPLETED(3),
    CANCELLED(4);

    private final int value;

    ShowTimeStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ShowTimeStatus fromValue(int value) {
        for (ShowTimeStatus status : ShowTimeStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }

    public static int toValue(ShowTimeStatus status) {
        return status.getValue();
    }
}
