package com.example.cinematicket.enums;


import lombok.Getter;

@Getter
public enum RoomStatus {
    AVAILABLE(0),
    OCCUPIED(1),
    MAINTENANCE(2);

    private final int value;

    RoomStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RoomStatus fromValue(int value) {
        for (RoomStatus status : RoomStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }

    public static int toValue(RoomStatus status) {
        return status.getValue();
    }
}
