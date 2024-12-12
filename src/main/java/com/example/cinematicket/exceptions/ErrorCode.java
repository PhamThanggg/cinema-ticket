package com.example.cinematicket.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Lỗi chưa được phân loại", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Khóa không hợp lệ", HttpStatus.BAD_REQUEST),
    INVALID_DOB(1008, "Bạn phải phải đủ {min} tuổi", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1007, "Bạn không có quyền truy cập", HttpStatus.FORBIDDEN),

    ADDRESS_NOT_BLANK(1009, "Địa chỉ là bắt buộc", HttpStatus.BAD_REQUEST),
    ADDRESS_VALID(1010, "Địa chỉ phải có ít nhất {min} ký tự và không quá {max} ký tự.", HttpStatus.BAD_REQUEST),
    PRICE_NOT_NULL(1011, "Giá là bắt buộc", HttpStatus.BAD_REQUEST),
    PRICE_INVALID(1012, "Mã giá phải lớn hơn hoặc bằng {value}", HttpStatus.BAD_REQUEST),
    DATE_VALID(1013, "Định dạng phải là yyyy-MM-dd", HttpStatus.BAD_REQUEST),
    DATE_VALID_PAST(1013, "Ngày sinh phải trong quá khứ", HttpStatus.BAD_REQUEST),
    TITLE_NOT_BLANK(1604, "Tiêu đề là bắt buộc", HttpStatus.BAD_REQUEST),
    TITLE_VALID(1605, "Tiêu đề phải có ít nhất {min} ký tự và không quá {max} ký tự.", HttpStatus.BAD_REQUEST),
    START_VALID(1606, "Xếp hạng sao phải từ từ 0 đến 5", HttpStatus.BAD_REQUEST),
    NAME_NOT_BLANK(1607, "Tên là bắt buộc", HttpStatus.BAD_REQUEST),
    NAME_VALID(1608, "Tên phải có ít nhất {min} ký tự và không quá {max} ký tự.", HttpStatus.BAD_REQUEST),
    TOKEN_NOT_BLANK(1607, "Token là bắt buộc", HttpStatus.BAD_REQUEST),
    TOKEN_VALID(1608, "Token phải có ít nhất {min} ký tự và không quá {max} ký tự.", HttpStatus.BAD_REQUEST),
    PHONE_FORMAT(1607, "Số điện thoại không đúng định dạng", HttpStatus.BAD_REQUEST),
    PHONE_VALID(1608, "Số điện thoại phải có ít nhất {min} ký tự và không quá {max} ký tự.", HttpStatus.BAD_REQUEST),
    EMAIL_FORMAT(1607, "Email không đúng định dạng", HttpStatus.BAD_REQUEST),
    EMAIL_VALID(1608, "Email phải có ít nhất {min} ký tự và không quá {max} ký tự.", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1014, "Email phải có ít nhất {min} ký tự và không quá {max} ký tự.", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_BLANK(1607, "Email cannot be blank", HttpStatus.BAD_REQUEST),
    STAR_NUMBER(1607, "Điểm star phải >= 20 và <= 100", HttpStatus.BAD_REQUEST),
    STAR_SATISFY(1607, "Điểm star của bạn không đủ", HttpStatus.BAD_REQUEST),
    DATE_OF_BIRTH(1607, "Ngày tháng năm sinh k được trống", HttpStatus.BAD_REQUEST),
    DATE_PAST(1607, "Ngày tháng năm sinh phải là quá khứ", HttpStatus.BAD_REQUEST),
    DATE_FORMAT(1607, "Ngày tháng năm không đúng định dạng", HttpStatus.BAD_REQUEST),
    STATUS_LENGTH(1607, "Trạng thái không đúng", HttpStatus.BAD_REQUEST),
    STATUS_NOT_NULL(1607, "Ngày tháng năm không đúng định dạng", HttpStatus.BAD_REQUEST),
    VALUE_TOO_LARGE(1607, "Giá trị bạn nhập quá lớn", HttpStatus.BAD_REQUEST),
    ID_MIN(1607, "ID nhập vào phải > 0", HttpStatus.BAD_REQUEST),
    USER_ID_NOT_NULL(1607, "ID người dùng là bắt buộc", HttpStatus.BAD_REQUEST),
    SEAT_ID_NOT_NULL(1607, "ID ghế là bắt buộc", HttpStatus.BAD_REQUEST),
    SCHEDULE_ID_NOT_NULL(1607, "ID lịch chiếu là bắt buộc", HttpStatus.BAD_REQUEST),

    START_DATE(1607, "Ngày bắt đầu không được để trống", HttpStatus.BAD_REQUEST),
    END_DATE(1607, "Ngày kết thúc không được để trống", HttpStatus.BAD_REQUEST),

    TIME_RESERVATION_NOT_NULL(2513, "Thời gian đặt là bắt buộc", HttpStatus.BAD_REQUEST),
    TIME_EXPIRY_NOT_NULL(2513, "Thời gian kết thúc là bắt buộc", HttpStatus.BAD_REQUEST),

    //=================== USER BEGIN =====================
    PASSWORD_NOT_SAME(1001, "Mật khẩu không khớp", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "Email đã tồn tại", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1003, "Người dùng không tồn tại", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1004, "Chưa thực hiện đăng nhập", HttpStatus.UNAUTHORIZED),
    //=================== USER END =======================

    //=================== CINEMA BEGIN =====================
    CINEMA_NANE_BLANK(1101, "Tên rạp chiếu là bắt buộc", HttpStatus.BAD_REQUEST),
    CINEMA_NAME_VALID(1102, "Tên rạp chiếu phải có độ dài từ {min} đến {max} ký tự", HttpStatus.BAD_REQUEST),

    CINEMA_EXISTED(1103, "Rạp chiếu đã tồn tại", HttpStatus.BAD_REQUEST),
    CINEMA_NOT_EXISTED(1104, "Rạp chiếu không tồn tại", HttpStatus.BAD_REQUEST),
    CINEMA_NOT_NULL(1105, "Rạp chiếu là bắt buộc", HttpStatus.BAD_REQUEST),
    CINEMA_VALID(1106, "Mã rạp chiếu phải lớn hơn hoặc bằng {min}", HttpStatus.BAD_REQUEST),
    //=================== CINEMA END =======================

    //=================== CINEMA ROOM BEGIN =====================
    CINEMA_ROOM_EXISTED(1201, "Phòng chiếu đã tồn tại", HttpStatus.BAD_REQUEST),
    CINEMA_ROOM_NOT_EXISTED(1202, "Phòng chiếu không tồn tại", HttpStatus.BAD_REQUEST),

    ROOM_NAME_NOT_BLANK(1203, "Tên phòng chiếu là bắt buộc", HttpStatus.BAD_REQUEST),
    ROOM_NAME_VALID(1204, "Tên phòng chiếu phải có độ dài từ {min} đến {max} ký tự", HttpStatus.BAD_REQUEST),
    CINEMA_ROOM_NOT_NULL(1206, "Phòng chiếu là bắt buộc", HttpStatus.BAD_REQUEST),
    CINEMA_ROOM_VALID(1207, "Mã phòng chiếu phải lớn hơn hoặc bằng {value}", HttpStatus.BAD_REQUEST),

    ROW_MIN(1207, "Số hàng phải >= {value}", HttpStatus.BAD_REQUEST),
    ROW_MAX(1207, "Số hàng phải <= {value}", HttpStatus.BAD_REQUEST),
    COLUMN_MIN(1207, "Số cột phải >= {value}", HttpStatus.BAD_REQUEST),
    COLUMN_MAX(1207, "Số cột phải <= {value}", HttpStatus.BAD_REQUEST),
    ROW_NOT_NULL(1203, "Số hàng là bắt buộc", HttpStatus.BAD_REQUEST),
    COLUMN_NOT_NULL(1203, "Số cột là bắt buộc", HttpStatus.BAD_REQUEST),

    //=================== CINEMA ROOM END =======================

    //=================== CINEMA ROOM TYPE BEGIN =====================
    ROOM_TYPE_EXISTED(1301, "Loại phòng chiếu đã tồn tại", HttpStatus.BAD_REQUEST),
    ROOM_TYPE_NOT_EXISTED(1302, "Loại phòng chiếu không tồn tại", HttpStatus.BAD_REQUEST),

    ROOM_TYPE_NOT_NULL(1105, "Loại phòng chiếu là bắt buộc", HttpStatus.BAD_REQUEST),
    ROOM_TYPE_VALID(1106, "Mã loại phòng chiếu phải lớn hơn hoặc bằng {min}", HttpStatus.BAD_REQUEST),
    //=================== CINEMA ROOM TYPE END =======================

    //=================== CINEMA SEAT BEGIN =====================
    CINEMA_SEAT_EXISTED(1401, "Ghế rạp chiếu đã tồn tại", HttpStatus.BAD_REQUEST),
    CINEMA_SEAT_NOT_EXISTED(1402, "Ghế rạp chiếu không tồn tại", HttpStatus.BAD_REQUEST),
    CINEMA_SEAT_INDEX_EXISTED(1403, "Hàng và cột này đã có ghế", HttpStatus.BAD_REQUEST),

    CINEMA_SEAT_NAME_BLANK(1404, "Tên ghế là bắt buộc", HttpStatus.BAD_REQUEST),
    CINEMA_SEAT_NAME_VALID(1405, "Tên ghế phải có độ dài từ {min} đến {max} ký tự", HttpStatus.BAD_REQUEST),
    CINEMA_SEAT_BLANK(1404, "Ghế rạp chiếu là bắt buộc", HttpStatus.BAD_REQUEST),
    CINEMA_SEAT_VALID(1405, "Mã ghế rạp chiếu phải lớn hơn hoặc bằng {value}", HttpStatus.BAD_REQUEST),
    SEAT_TYPE_NOT_NULL(1408, "Loại ghế là bắt buộc", HttpStatus.BAD_REQUEST),
    SEAT_TYPE_VALID(1409, "Mã loại ghế phải lớn hơn hoặc bằng {value}", HttpStatus.BAD_REQUEST),
    ROW_VALID(1411, "Hàng phải có độ dài từ {min} đến {max} ký tự", HttpStatus.BAD_REQUEST),
    COLUM_NOT_NULL(1412, "Tên cột là bắt buộc", HttpStatus.BAD_REQUEST),
    COLUM_VALID(1413, "Cột phải có độ dài từ {min} đến {max} ký tự", HttpStatus.BAD_REQUEST),
    SEAT_COUNT(1405, "Chỉ thực hiện chức năng này khi phòng không có ghế", HttpStatus.BAD_REQUEST),
    //=================== CINEMA SEAT END =======================

    //=================== GENRE BEGIN =====================
    GENRE_EXISTED(1501, "Thể loại đã tồn tại", HttpStatus.BAD_REQUEST),
    GENRE_NOT_EXISTED(1502, "Thể loại không tồn tại", HttpStatus.BAD_REQUEST),

    GENRE_NOT_NULL(1503, "Thể loại là bắt buộc", HttpStatus.BAD_REQUEST),
    GENRE_VALID(1504, "Mã thể loại phải lớn hơn hoặc bằng {min}", HttpStatus.BAD_REQUEST),

    //=================== GENRE END =======================

    //=================== MOVIE BEGIN =====================
    MOVIE_EXISTED(1601, "Phim đã tồn tại", HttpStatus.BAD_REQUEST),
    MOVIE_NOT_EXISTED(1602, "Phim không tồn tại", HttpStatus.BAD_REQUEST),

    MOVIE_NOT_NULL(1603, "Phim là bắt buộc", HttpStatus.BAD_REQUEST),
    MOVIE_VALID(1604, "Mã phim phải lớn hơn hoặc bằng {min}", HttpStatus.BAD_REQUEST),
    MOVIE_NAME_NOT_BLANK(1605, "Tên phim là bắt buộc", HttpStatus.BAD_REQUEST),
    MOVIE_NAME_VALID(1606, "Tên phim phải có độ dài từ {min} đến {max} ký tự", HttpStatus.BAD_REQUEST),
    MOVIE_PRODUCER_NOT_BLANK(1607, "Tên nhà sản xuất là bắt buộc", HttpStatus.BAD_REQUEST),
    MOVIE_PRODUCER_VALID(1608, "Tên nhà sản xuất phải có độ dài từ {min} đến {max} ký tự", HttpStatus.BAD_REQUEST),

    DURATION_NOT_BLANK(1609, "Thời gian chiếu là bắt buộc", HttpStatus.BAD_REQUEST),
    LANGUAGE_NOT_BLANK(1610, "Tên ngôn ngữ là bắt buộc", HttpStatus.BAD_REQUEST),
    LANGUAGE_VALID(1611, "Tên ngôn ngữ phải có độ dài từ {min} đến {max} ký tự", HttpStatus.BAD_REQUEST),
    AGE_NOT_NULL(1612, "Độ tuổi là bắt buộc", HttpStatus.BAD_REQUEST),
    AGE_VALID_MIN(1613, "Giới hạn độ tuổi phải lớn hơn hoặc bằng {min}", HttpStatus.BAD_REQUEST),
    AGE_VALID_MAX(1614, "Giới hạn độ tuổi phải lớn hơn hoặc bằng {min}", HttpStatus.BAD_REQUEST),
    //=================== MOVIE END =======================

    //=================== CINEMA SEAT BEGIN =====================
    SEAT_TYPE_EXISTED(1701, "Loại ghế đã tồn tại", HttpStatus.BAD_REQUEST),
    SEAT_TYPE_NOT_EXISTED(1702, "Loại ghế không tồn tại", HttpStatus.BAD_REQUEST),

    SEAT_BOOKING(1701, "Ghế đã được đặt hoặc giữ chỗ", HttpStatus.BAD_REQUEST),
    //=================== CINEMA SEAT END =======================


    //=================== AREA BEGIN =====================
    AREA_NOT_NULL(1801, "Tên khu vực là bắt buộc", HttpStatus.BAD_REQUEST),
    AREA_INVALID(1802, "Tên khu vực phải có độ dài từ {min} đến {max} ký tự", HttpStatus.BAD_REQUEST),

    AREA_EXISTS(1802, "Tên khu vực đã tồn tại", HttpStatus.BAD_REQUEST),
    AREA_NOT_EXISTS(1802, "Khu vực không tồn tại", HttpStatus.BAD_REQUEST),
    AREA_BLANK(1803, "Mã khu vực là bắt buộc", HttpStatus.BAD_REQUEST),
    AREA_VALID(1804, "Mã khu vực phải lớn hơn hoặc bằng {value}", HttpStatus.BAD_REQUEST),
    //=================== AREA END =======================

    //=================== COMMENT BEGIN =====================
    COMMENT_NOT_NULL(1901, "Tên bình luận là bắt buộc", HttpStatus.BAD_REQUEST),
    COMMENT_INVALID(1902, "Tên bình luận phải có độ dài từ {min} đến {max} ký tự", HttpStatus.BAD_REQUEST),

    COMMENT_EXISTS(1902, "Bình luận đã tồn tại", HttpStatus.BAD_REQUEST),
    COMMENT_NOT_EXISTS(1902, "Bình luận không tồn tại", HttpStatus.BAD_REQUEST),
    COMMENT_UPDATE(1902, "Bạn không thể cập nhật bình luận của người khác", HttpStatus.BAD_REQUEST),
    COMMENT_DELETE(1902, "Bạn không thể xóa bình luận của người khác", HttpStatus.BAD_REQUEST),

    //=================== INVOICE BEGIN =====================
    INVOICE_TOTAL_NOT_NULL(2001, "Số tiền tổng cộng là bắt buộc", HttpStatus.BAD_REQUEST),
    INVOICE_TOTAL_INVALID(2002, "Số tiền tổng cộng phải lớn hơn 0", HttpStatus.BAD_REQUEST),

    INVOICE_EXISTS(2003, "Hóa đơn đã tồn tại", HttpStatus.BAD_REQUEST),
    INVOICE_NOT_EXISTS(2004, "Hóa đơn không tồn tại", HttpStatus.BAD_REQUEST),

    SCHEDULE_BEGIN(2004, "Bạn không thể đặt vé khi đã đến giờ chiếu", HttpStatus.BAD_REQUEST),
    SEAT_BOOKED(2004, "Ghế bạn chọn đã có người đặt", HttpStatus.BAD_REQUEST),
    //=================== INVOICE END =======================

    //=================== AREA BEGIN =====================
    ITEM_NOT_NULL(2101, "Tên món hàng là bắt buộc", HttpStatus.BAD_REQUEST),
    ITEM_INVALID(2102, "Tên món hàng phải có độ dài từ {min} đến {max} ký tự", HttpStatus.BAD_REQUEST),

    ITEM_EXISTS(2102, "Món hàng đã tồn tại", HttpStatus.BAD_REQUEST),
    ITEM_NOT_EXISTS(2102, "Món hàng không tồn tại", HttpStatus.BAD_REQUEST),
    DESCRIPTION_INVALID(2102, "Mô tả không được quá {max} ký tự", HttpStatus.BAD_REQUEST),
//=================== AREA END =======================

    //=================== MOVIE IMAGE BEGIN =====================
    IMAGE_NOT_NULL(2201, "Tên hình ảnh là bắt buộc", HttpStatus.BAD_REQUEST),
    IMAGE_INVALID(2202, "Tên hình ảnh phải có độ dài từ {min} đến {max} ký tự", HttpStatus.BAD_REQUEST),

    IMAGE_EXISTS(2202, "Hình ảnh đã tồn tại", HttpStatus.BAD_REQUEST),
    IMAGE_NOT_EXISTS(2202, "Hình ảnh không tồn tại", HttpStatus.BAD_REQUEST),
//=================== MOVIE IMAGE END =======================

    //=================== MOVIE PEOPLE BEGIN =====================
    MOVIE_PP_NOT_BLANK(2301, "Tên người tham gia phim là bắt buộc", HttpStatus.BAD_REQUEST),
    MOVIE_PP_INVALID(2302, "Tên người tham gia phim phải có độ dài từ {min} đến {max} ký tự", HttpStatus.BAD_REQUEST),

    MOVIE_PP_EXISTS(2302, "Tên người tham gia phim đã tồn tại", HttpStatus.BAD_REQUEST),
    MOVIE_PP_NOT_EXISTS(2302, "Người tham gia phim không tồn tại", HttpStatus.BAD_REQUEST),
    //=================== MOVIE PEOPLE END =======================

    //=================== ROLE TYPE BEGIN =====================
    ROLE_TYPE_NOT_NULL(2401, "Loại vai trò là bắt buộc", HttpStatus.BAD_REQUEST),
    ROLE_TYPE_INVALID(2402, "ID loại vai trò phải lớn hơn {value}", HttpStatus.BAD_REQUEST),

    ROLE_TYPE_EXISTS(2402, "Tên loại vai trò đã tồn tại", HttpStatus.BAD_REQUEST),
    ROLE_TYPE_NOT_EXISTS(2402, "Loại vai trò không tồn tại", HttpStatus.BAD_REQUEST),
//=================== ROLE TYPE END =======================

    //=================== SCHEDULE BEGIN =====================
    SCHEDULE_NOT_NULL(2501, "Lịch trình là bắt buộc", HttpStatus.BAD_REQUEST),
    SCHEDULE_INVALID(2502, "ID lịch trình phải lớn hơn {value}", HttpStatus.BAD_REQUEST),

    SCHEDULE_EXISTS(2502, "Tên lịch trình đã tồn tại", HttpStatus.BAD_REQUEST),
    SCHEDULE_NOT_EXISTS(2502, "Lịch trình không tồn tại", HttpStatus.BAD_REQUEST),
    DATE_SCREEN_NOT_NULL(2513, "Ngày chiếu là bắt buộc", HttpStatus.BAD_REQUEST),
    TIME_START_NOT_NULL(2513, "Thời gian bắt đầu là bắt buộc", HttpStatus.BAD_REQUEST),
    TIME_END_NOT_NULL(2513, "Thời gian kết thúc là bắt buộc", HttpStatus.BAD_REQUEST),

    SCHEDULE_TIME_EXISTS(2502, "Khoảng thời gian bạn chọn đã có lịch chiếu", HttpStatus.BAD_REQUEST),
    SCHEDULE_EDIT(2502, "Bạn không thể sửa lịch chiếu khi lịch chiếu sắp diễn ra", HttpStatus.BAD_REQUEST),
    SCHEDULE_DATE_SAME(2502, "Ngày chiếu phim và thời gian bắt đầu chiếu phim phải cùng ngày", HttpStatus.BAD_REQUEST),
    SCHEDULE_DATE(2502, "Ngày chiếu phải lớn hơn hoặc bằng ngày hôm nay", HttpStatus.BAD_REQUEST),
    SCHEDULE_TIME(2502, "Bạn phải lên lịch trước ít nhất 60 phút khu giờ chiếu bắt đầu", HttpStatus.BAD_REQUEST),
    SCHEDULE_TIME_MOVIE(2502,
            "Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc " +
                    "và tổng thời gian phải bằng thời gian xem phim cộng thêm 10 phút chờ",
            HttpStatus.BAD_REQUEST),
//=================== SCHEDULE END =======================

    //=================== TICKET BEGIN =====================
    TICKET_NOT_NULL(2501, "Vé là bắt buộc", HttpStatus.BAD_REQUEST),
    TICKET_INVALID(2502, "ID vé phải lớn hơn {value}", HttpStatus.BAD_REQUEST),

    TICKET_EXISTS(2502, "Tên vé đã tồn tại", HttpStatus.BAD_REQUEST),
    TICKET_NOT_EXISTS(2502, "Vé không tồn tại", HttpStatus.BAD_REQUEST),
    //=================== TICKET END =======================

    //=================== TICKET TYPE BEGIN =====================
    TICKET_TYPE_NOT_NULL(2501, "Loại vé là bắt buộc", HttpStatus.BAD_REQUEST),
    TICKET_TYPE_INVALID(2502, "ID loại vé phải lớn hơn {value}", HttpStatus.BAD_REQUEST),

    TICKET_TYPE_EXISTS(2602, "Tên loại vé đã tồn tại", HttpStatus.BAD_REQUEST),
    TICKET_TYPE_NOT_EXISTS(2602, "Loại vé không tồn tại", HttpStatus.BAD_REQUEST),
//=================== TICKET TYPE END =======================


    //=================== USER BEGIN =====================
    USER_EXISTS(2602, "Người dùng đã tồn tại", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTS(2602, "Người dùng không tồn tại", HttpStatus.BAD_REQUEST),

    USER_NOT_NULL(2501, "Người dùng là bắt buộc", HttpStatus.BAD_REQUEST),
    USER_INVALID(2502, "ID loại vé phải lớn hơn {value}", HttpStatus.BAD_REQUEST),
    GENDER_NOT_BLANK(2501, "Giới tính không được để trống", HttpStatus.BAD_REQUEST),
    GENDER_FORMAT(2501, "Giới tính không đúng định dạng", HttpStatus.BAD_REQUEST),

    PASSWORD_NOT_BLANK(2301, "Mật khẩu là bắt buộc", HttpStatus.BAD_REQUEST),
    REPASSWORD_NOT_BLANK(2301, "Nhập lại mật khẩu là bắt buộc", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(2302, "Mật khẩu phải có từ {min} đến {max} ký tự", HttpStatus.BAD_REQUEST),
    PASSWORD_CHANGE(2302, "Mật khẩu cũ không đúng", HttpStatus.BAD_REQUEST),
    PASSWORD_EQUAL(2302, "Mật khẩu không khớp", HttpStatus.BAD_REQUEST),
    PASSWORD_FORMAT(2302, "Mật khẩu phải chứa từ 8 đến 64 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt.", HttpStatus.BAD_REQUEST),
//=================== USER END =======================


    //=================== PERMISSION BEGIN =====================
    PERMISSION_NOT_BLANK(2501, "Quyền là bắt buộc", HttpStatus.BAD_REQUEST),
    PERMISSION_INVALID(2502, "ID quyền phải lớn hơn {value}", HttpStatus.BAD_REQUEST),

    PERMISSION_EXISTS(2602, "Tên quyền đã tồn tại", HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_EXISTS(2602, "Quyền không tồn tại", HttpStatus.BAD_REQUEST),
//=================== PERMISSION END =======================

    //=================== ROLE BEGIN =====================
    ROLE_NOT_BLANK(2501, "Vai trò là bắt buộc", HttpStatus.BAD_REQUEST),
    ROLE_INVALID(2502, "ID vai trò phải lớn hơn {value}", HttpStatus.BAD_REQUEST),

    ROLE_EXISTS(2602, "Tên vai trò đã tồn tại", HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTS(2602, "Vai trò không tồn tại", HttpStatus.BAD_REQUEST),
    //=================== ROLE END =======================

    //=================== PROMOTION BEGIN =====================
    PROMOTION_NOT_BLANK(2501, "Khuyễn mãi là bắt buộc", HttpStatus.BAD_REQUEST),
    PROMOTION_INVALID(2502, "ID khuyến mãi phải không lớn hơn {value}", HttpStatus.BAD_REQUEST),

    PROMOTION_EXISTS(2602, "Tên khuyến mãi đã tồn tại." ,HttpStatus.BAD_REQUEST),
    PROMOTION_NOT_EXISTS(2602, "Khuyến mãi không tồn tại", HttpStatus.BAD_REQUEST),

    PROMOTION_NAME_IS_REQUIRED(2602, "Tên khuyến mãi là bắt buộc", HttpStatus.BAD_REQUEST),
    PROMOTION_NAME_NOT_BLANK(2602, "Tên khuyến mãi phải có ít nhất {min} ký tự và không quá {max} ký tực", HttpStatus.BAD_REQUEST),
    DISCOUNT_MAX(2502, "Giảm giá không được vượt quá {value}%", HttpStatus.BAD_REQUEST),
    DISCOUNT_MIN(2502, "Giảm giá không được âm", HttpStatus.BAD_REQUEST),
    DISCOUNT(2502, "% giảm giá không được để trống", HttpStatus.BAD_REQUEST),


    //=================== PROMOTION END =======================

    ;


    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}
