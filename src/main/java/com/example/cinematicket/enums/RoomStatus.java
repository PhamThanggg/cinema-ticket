package com.example.cinematicket.enums;


import lombok.Getter;

@Getter
public enum RoomStatus {
    AVAILABLE(0), // hoạt động
    OCCUPIED(1), // dừng hoạt động
    MAINTENANCE(2); // đang bảo trì

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
