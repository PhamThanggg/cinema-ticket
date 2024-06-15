package com.example.cinematicket.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
@AllArgsConstructor
public enum ErrorCode {

    //=================== USER BEGIN =====================
    PASSWORD_NOT_SAME(1001, "Passwords are not the same", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "Email existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1003, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1004, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    //=================== USER END =======================

    //=================== CINEMA BEGIN =====================
    CINEMA_EXISTED(1101, "Cinema existed", HttpStatus.BAD_REQUEST),
    CINEMA_NOT_EXISTED(1102, "Cinema not existed", HttpStatus.BAD_REQUEST),
    //=================== CINEMA END =======================

    //=================== CINEMA ROOM BEGIN =====================
    CINEMA_ROOM_EXISTED(1201, "Cinema room existed", HttpStatus.BAD_REQUEST),
    CINEMA_ROOM_NOT_EXISTED(1202, "Cinema room not existed", HttpStatus.BAD_REQUEST),
    //=================== CINEMA ROOM END =======================

    //=================== CINEMA ROOM TYPE BEGIN =====================
    ROOM_TYPE_EXISTED(1301, "Cinema room existed", HttpStatus.BAD_REQUEST),
    ROOM_TYPE_NOT_EXISTED(1302, "Cinema room not existed", HttpStatus.BAD_REQUEST),
    //=================== CINEMA ROOM TYPE END =======================

    //=================== CINEMA SEAT BEGIN =====================
    CINEMA_SEAT_EXISTED(1401, "Cinema seat existed", HttpStatus.BAD_REQUEST),
    CINEMA_SEAT_NOT_EXISTED(1402, "Cinema seat not existed", HttpStatus.BAD_REQUEST),
    CINEMA_SEAT_INDEX_EXISTED(1403, "Rows and columns already exist chairs", HttpStatus.BAD_REQUEST),
    //=================== CINEMA SEAT END =======================

    //=================== CINEMA SEAT BEGIN =====================
    GENRE_EXISTED(1501, "Genre existed", HttpStatus.BAD_REQUEST),
    GENRE_NOT_EXISTED(1502, "Genre not existed", HttpStatus.BAD_REQUEST),

    //=================== CINEMA SEAT END =======================

    //=================== CINEMA SEAT BEGIN =====================
    MOVIE_EXISTED(1601, "Movie existed", HttpStatus.BAD_REQUEST),
    MOVIE_NOT_EXISTED(1602, "Movie not existed", HttpStatus.BAD_REQUEST),

    //=================== CINEMA SEAT END =======================

    //=================== CINEMA SEAT BEGIN =====================
    SEAT_TYPE_EXISTED(1701, "Movie existed", HttpStatus.BAD_REQUEST),
    SEAT_TYPE_NOT_EXISTED(1702, "Movie not existed", HttpStatus.BAD_REQUEST),

    //=================== CINEMA SEAT END =======================
    ;


    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}
