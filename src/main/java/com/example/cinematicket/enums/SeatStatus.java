package com.example.cinematicket.enums;


import lombok.Getter;

@Getter
public enum SeatStatus {
    AVAILABLE(0),
    BOOKED(1),
    RESERVED(2);

    private final int value;

    SeatStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SeatStatus fromValue(int value) {
        for (SeatStatus status : SeatStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }

    public static int toValue(SeatStatus status) {
        return status.getValue();
    }
}
