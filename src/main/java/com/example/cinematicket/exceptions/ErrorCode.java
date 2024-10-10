package com.example.cinematicket.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),

    ADDRESS_NOT_BLANK(1009, "Address is required", HttpStatus.BAD_REQUEST),
    ADDRESS_VALID(1010, "Address must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
    PRICE_NOT_NULL(1011, "Price is required", HttpStatus.BAD_REQUEST),
    PRICE_INVALID(1012, "Price ID must be greater than or equal to {value}", HttpStatus.BAD_REQUEST),
    DATE_VALID(1013, "Format must be yyyy-MM-dd", HttpStatus.BAD_REQUEST),
    DATE_VALID_PAST(1013, "Date of Birth must be in the past", HttpStatus.BAD_REQUEST),
    TITLE_NOT_BLANK(1604, "Title is required", HttpStatus.BAD_REQUEST),
    TITLE_VALID(1605, "Title must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
    START_VALID(1606, "Star rating must be between 0 and 5", HttpStatus.BAD_REQUEST),
    NAME_NOT_BLANK(1607, "Name is required", HttpStatus.BAD_REQUEST),
    NAME_VALID(1608, "Name must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
    TOKEN_NOT_BLANK(1607, "Token is required", HttpStatus.BAD_REQUEST),
    TOKEN_VALID(1608, "Token must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
    PHONE_FORMAT(1607, "Phone number is not in correct format", HttpStatus.BAD_REQUEST),
    PHONE_VALID(1608, "Phone must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
    EMAIL_FORMAT(1607, "Email is not in correct format", HttpStatus.BAD_REQUEST),
    EMAIL_VALID(1608, "Email must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_BLANK(1607, "Email cannot be blank", HttpStatus.BAD_REQUEST),

    //=================== USER BEGIN =====================
    PASSWORD_NOT_SAME(1001, "Passwords are not the same", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "Email existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1003, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1004, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    //=================== USER END =======================

    //=================== CINEMA BEGIN =====================
    CINEMA_NANE_BLANK(1101, "Cinema name is required", HttpStatus.BAD_REQUEST),
    CINEMA_NAME_VALID(1102, "Cinema name must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),

    CINEMA_EXISTED(1103, "Cinema existed", HttpStatus.BAD_REQUEST),
    CINEMA_NOT_EXISTED(1104, "Cinema not existed", HttpStatus.BAD_REQUEST),
    CINEMA_NOT_NULL(1105, "Cinema is required", HttpStatus.BAD_REQUEST),
    CINEMA_VALID(1106, "Cinema ID must be greater than or equal to {min}", HttpStatus.BAD_REQUEST),
    //=================== CINEMA END =======================

    //=================== CINEMA ROOM BEGIN =====================
    CINEMA_ROOM_EXISTED(1201, "Cinema room existed", HttpStatus.BAD_REQUEST),
    CINEMA_ROOM_NOT_EXISTED(1202, "Cinema room not existed", HttpStatus.BAD_REQUEST),

    ROOM_NAME_NOT_BLANK(1203, "Room name is required", HttpStatus.BAD_REQUEST),
    ROOM_NAME_VALID(1204, "Room name must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
    CINEMA_ROOM_NOT_NULL(1206, "Cinema room is required", HttpStatus.BAD_REQUEST),
    CINEMA_ROOM_VALID(1207, "Cinema room ID must be greater than or equal to {value}", HttpStatus.BAD_REQUEST),
    //=================== CINEMA ROOM END =======================

    //=================== CINEMA ROOM TYPE BEGIN =====================
    ROOM_TYPE_EXISTED(1301, "Cinema room existed", HttpStatus.BAD_REQUEST),
    ROOM_TYPE_NOT_EXISTED(1302, "Cinema room not existed", HttpStatus.BAD_REQUEST),

    ROOM_TYPE_NOT_NULL(1105, "Room type is required", HttpStatus.BAD_REQUEST),
    ROOM_TYPE_VALID(1106, "Room type ID must be greater than or equal to {min}", HttpStatus.BAD_REQUEST),
    //=================== CINEMA ROOM TYPE END =======================

    //=================== CINEMA SEAT BEGIN =====================
    CINEMA_SEAT_EXISTED(1401, "Cinema seat existed", HttpStatus.BAD_REQUEST),
    CINEMA_SEAT_NOT_EXISTED(1402, "Cinema seat not existed", HttpStatus.BAD_REQUEST),
    CINEMA_SEAT_INDEX_EXISTED(1403, "Rows and columns already exist chairs", HttpStatus.BAD_REQUEST),

    CINEMA_SEAT_NAME_BLANK(1404, "Seat name is required", HttpStatus.BAD_REQUEST),
    CINEMA_SEAT_NAME_VALID(1405, "Seat name must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
    CINEMA_SEAT_BLANK(1404, "Cinema seat is required", HttpStatus.BAD_REQUEST),
    CINEMA_SEAT_VALID(1405, "Cinema Seat ID must be greater than or equal to {value}", HttpStatus.BAD_REQUEST),
    SEAT_TYPE_NOT_NULL(1408, "Seat type is required", HttpStatus.BAD_REQUEST),
    SEAT_TYPE_VALID(1409, "Seat type ID must be greater than or equal to {value}", HttpStatus.BAD_REQUEST),
    ROW_NOT_NULL(1410, "Row name is required", HttpStatus.BAD_REQUEST),
    ROW_VALID(1411, "Row must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
    COLUM_NOT_NULL(1412, "Column name is required", HttpStatus.BAD_REQUEST),
    COLUM_VALID(1413, "Column must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
    //=================== CINEMA SEAT END =======================

    //=================== GENRE BEGIN =====================
    GENRE_EXISTED(1501, "Genre existed", HttpStatus.BAD_REQUEST),
    GENRE_NOT_EXISTED(1502, "Genre not existed", HttpStatus.BAD_REQUEST),

    GENRE_NOT_NULL(1503, "Genre is required", HttpStatus.BAD_REQUEST),
    GENRE_VALID(1504, "Genre ID must be greater than or equal to {min}", HttpStatus.BAD_REQUEST),

    //=================== GENRE END =======================

    //=================== MOVIE BEGIN =====================
    MOVIE_EXISTED(1601, "Movie existed", HttpStatus.BAD_REQUEST),
    MOVIE_NOT_EXISTED(1602, "Movie not existed", HttpStatus.BAD_REQUEST),

    MOVIE_NOT_NULL(1603, "Movie is required", HttpStatus.BAD_REQUEST),
    MOVIE_VALID(1604, "Movie ID must be greater than or equal to {min}", HttpStatus.BAD_REQUEST),
    MOVIE_NAME_NOT_BLANK(1605, "Movie name is required", HttpStatus.BAD_REQUEST),
    MOVIE_NAME_VALID(1606, "Movie name must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
    MOVIE_PRODUCER_NOT_BLANK(1607, "Producer name is required", HttpStatus.BAD_REQUEST),
    MOVIE_PRODUCER_VALID(1608, "Producer name must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),

    DURATION_NOT_BLANK(1609, "Duration is required", HttpStatus.BAD_REQUEST),
    LANGUAGE_NOT_BLANK(1610, "Language name is required", HttpStatus.BAD_REQUEST),
    LANGUAGE_VALID(1611, "Language name must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
    AGE_NOT_NULL(1612, "Movie is required", HttpStatus.BAD_REQUEST),
    AGE_VALID_MIN(1613, "Age limit must be greater than or equal to {min}", HttpStatus.BAD_REQUEST),
    AGE_VALID_MAX(1614, "Age limit must be greater than or equal to {min}", HttpStatus.BAD_REQUEST),
    //=================== MOVIE END =======================

    //=================== CINEMA SEAT BEGIN =====================
    SEAT_TYPE_EXISTED(1701, "Movie existed", HttpStatus.BAD_REQUEST),
    SEAT_TYPE_NOT_EXISTED(1702, "Movie not existed", HttpStatus.BAD_REQUEST),

    //=================== CINEMA SEAT END =======================


    //=================== AREA BEGIN =====================
    AREA_NOT_NULL(1801, "Area name is required", HttpStatus.BAD_REQUEST),
    AREA_INVALID(1802, "Area name must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),

    AREA_EXISTS(1802, "Area name exists", HttpStatus.BAD_REQUEST),
    AREA_NOT_EXISTS(1802, "Area not exists", HttpStatus.BAD_REQUEST),
    AREA_BLANK(1803, "Area id is required", HttpStatus.BAD_REQUEST),
    AREA_VALID(1804, "Area ID must be greater than or equal to {value}", HttpStatus.BAD_REQUEST),
    //=================== AREA END =======================

    //=================== COMMENT BEGIN =====================
    COMMENT_NOT_NULL(1901, "Comment name is required", HttpStatus.BAD_REQUEST),
    COMMENT_INVALID(1902, "Comment name must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),

    COMMENT_EXISTS(1902, "Comment name exists", HttpStatus.BAD_REQUEST),
    COMMENT_NOT_EXISTS(1902, "Comment not exists", HttpStatus.BAD_REQUEST),
    COMMENT_UPDATE(1902, "You cannot update another user comment", HttpStatus.BAD_REQUEST),
    COMMENT_DELETE(1902, "You cannot delete another user comment", HttpStatus.BAD_REQUEST),
    //=================== COMMENT END =======================

    //=================== INVOICE BEGIN =====================
    INVOICE_TOTAL_NOT_NULL(2001, "Total amount is required", HttpStatus.BAD_REQUEST),
    INVOICE_TOTAL_INVALID(2002, "Total amount must be greater than 0", HttpStatus.BAD_REQUEST),

    INVOICE_EXISTS(2003, "Invoice name exists", HttpStatus.BAD_REQUEST),
    INVOICE_NOT_EXISTS(2004, "Invoice not exists", HttpStatus.BAD_REQUEST),
    //=================== INVOICE END =======================

    //=================== AREA BEGIN =====================
    ITEM_NOT_NULL(2101, "Item name is required", HttpStatus.BAD_REQUEST),
    ITEM_INVALID(2102, "Item name must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),

    ITEM_EXISTS(2102, "Item name exists", HttpStatus.BAD_REQUEST),
    ITEM_NOT_EXISTS(2102, "Item not exists", HttpStatus.BAD_REQUEST),
    //=================== AREA END =======================

    //=================== MOVIE IMAGE BEGIN =====================
    IMAGE_NOT_NULL(2201, "Image name is required", HttpStatus.BAD_REQUEST),
    IMAGE_INVALID(2202, "Image name must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),

    IMAGE_EXISTS(2202, "Image name exists", HttpStatus.BAD_REQUEST),
    IMAGE_NOT_EXISTS(2202, "Image not exists", HttpStatus.BAD_REQUEST),
    //=================== MOVIE IMAGE END =======================

    //=================== MOVIE PEOPLE BEGIN =====================
    MOVIE_PP_NOT_BLANK(2301, "Movie peolpe name is required", HttpStatus.BAD_REQUEST),
    MOVIE_PP_INVALID(2302, "Movie peolpe name must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),

    MOVIE_PP_EXISTS(2302, "Movie people name exists", HttpStatus.BAD_REQUEST),
    MOVIE_PP_NOT_EXISTS(2302, "Movie people not exists", HttpStatus.BAD_REQUEST),
    //=================== MOVIE PEOPLE END =======================

    //=================== ROLE TYPE BEGIN =====================
    ROLE_TYPE_NOT_NULL(2401, "Role type is required", HttpStatus.BAD_REQUEST),
    ROLE_TYPE_INVALID(2402, "Role type ID must be greater than {value}", HttpStatus.BAD_REQUEST),

    ROLE_TYPE_EXISTS(2402, "Role type name exists", HttpStatus.BAD_REQUEST),
    ROLE_TYPE_NOT_EXISTS(2402, "Role type not exists", HttpStatus.BAD_REQUEST),
    //=================== ROLE TYPE END =======================

    //=================== SCHEDULE BEGIN =====================
    SCHEDULE_NOT_NULL(2501, "Schedule is required", HttpStatus.BAD_REQUEST),
    SCHEDULE_INVALID(2502, "Schedule ID must be greater than {value}", HttpStatus.BAD_REQUEST),

    SCHEDULE_EXISTS(2502, "Schedule name exists", HttpStatus.BAD_REQUEST),
    SCHEDULE_NOT_EXISTS(2502, "Schedule not exists", HttpStatus.BAD_REQUEST),
    DATE_SCREEN_NOT_NULL(2513, "Screening Date is required", HttpStatus.BAD_REQUEST),
    TIME_START_NOT_NULL(2513, "Start time is required", HttpStatus.BAD_REQUEST),
    TIME_END_NOT_NULL(2513, "End time is required", HttpStatus.BAD_REQUEST),
    //=================== SCHEDULE END =======================

    //=================== TICKET BEGIN =====================
    TICKET_NOT_NULL(2501, "Ticket is required", HttpStatus.BAD_REQUEST),
    TICKET_INVALID(2502, "Ticket ID must be greater than {value}", HttpStatus.BAD_REQUEST),

    TICKET_EXISTS(2502, "Ticket name exists", HttpStatus.BAD_REQUEST),
    TICKET_NOT_EXISTS(2502, "Ticket not exists", HttpStatus.BAD_REQUEST),
    //=================== TICKET END =======================

    //=================== TICKET TYPE BEGIN =====================
    TICKET_TYPE_NOT_NULL(2501, "Ticket type is required", HttpStatus.BAD_REQUEST),
    TICKET_TYPE_INVALID(2502, "Ticket type ID must be greater than {value}", HttpStatus.BAD_REQUEST),

    TICKET_TYPE_EXISTS(2602, "Ticket type name exists", HttpStatus.BAD_REQUEST),
    TICKET_TYPE_NOT_EXISTS(2602, "Ticket type not exists", HttpStatus.BAD_REQUEST),
    //=================== TICKET TYPE END =======================


    //=================== USER BEGIN =====================
    USER_EXISTS(2602, "User exists", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTS(2602, "User not exists", HttpStatus.BAD_REQUEST),

    USER_NOT_NULL(2501, "User is required", HttpStatus.BAD_REQUEST),
    USER_INVALID(2502, "Ticket type ID must be greater than {value}", HttpStatus.BAD_REQUEST),
    GENDER_NOT_BLANK(2501, "Gender cannot be blank", HttpStatus.BAD_REQUEST),

    PASSWORD_NOT_BLANK(2301, "Password is required", HttpStatus.BAD_REQUEST),
    REPASSWORD_NOT_BLANK(2301, "Repassword is required", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(2302, "Password must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
    //=================== USER END =======================


    //=================== PERMISSION BEGIN =====================
    PERMISSION_NOT_BLANK(2501, "Permission is required", HttpStatus.BAD_REQUEST),
    PERMISSION_INVALID(2502, "Permission ID must be greater than {value}", HttpStatus.BAD_REQUEST),

    PERMISSION_EXISTS(2602, "Permission name exists", HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_EXISTS(2602, "Permission not exists", HttpStatus.BAD_REQUEST),
    //=================== PERMISSION END =======================

    //=================== ROLE BEGIN =====================
    ROLE_NOT_BLANK(2501, "Role is required", HttpStatus.BAD_REQUEST),
    ROLE_INVALID(2502, "Role ID must be greater than {value}", HttpStatus.BAD_REQUEST),

    ROLE_EXISTS(2602, "Role name exists", HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTS(2602, "Role not exists", HttpStatus.BAD_REQUEST),
    //=================== ROLE END =======================

    ;


    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}
