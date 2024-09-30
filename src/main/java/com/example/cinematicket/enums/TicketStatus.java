package com.example.cinematicket.enums;


import lombok.Getter;

@Getter
public enum TicketStatus {
    UNPAID(0),
    PAID(1),
    CANCELED(2),
    CHECKED_IN(3);

    private final int value;

    TicketStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TicketStatus fromValue(int value) {
        for (TicketStatus status : TicketStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
    public static int toValue(TicketStatus status) {
        return status.getValue();
    }
}
