package com.example.cinematicket.enums;


import lombok.Getter;

@Getter
public enum TypeStatus {
    ACTIVE(0),
    INACTIVE(1);

    private final int value;

    TypeStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TypeStatus fromValue(int value) {
        for (TypeStatus status : TypeStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }

    public static int toValue(TypeStatus status) {
        return status.getValue();
    }
}
