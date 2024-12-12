package com.example.cinematicket.enums;


import lombok.Getter;

@Getter
public enum SeatStatus {
    AVAILABLE(1), // hoạt động
    BOOKED(2), // khoảng trống
    RESERVED(3); // xóa mềm

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
