package com.example.cinematicket.enums;


import lombok.Getter;

@Getter
public enum MovieStatus {
    COMING_SOON(0),
    NOW_SHOWING(1),
    ENDED(2);

    private final int value;

    MovieStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MovieStatus fromValue(int value) {
        for (MovieStatus status : MovieStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }

    public static int toValue(MovieStatus status) {
        return status.getValue();
    }
}
