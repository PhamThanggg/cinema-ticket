package com.example.cinematicket.enums;


import lombok.Getter;

@Getter
public enum CinemaStatus {
    OPEN(0),
    CLOSED(1),
    UNDER_MAINTENANCE(2);

    private final int value;

    CinemaStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CinemaStatus fromValue(int value) {
        for (CinemaStatus status : CinemaStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }

    public static int toValue(CinemaStatus status) {
        return status.getValue();
    }
}
