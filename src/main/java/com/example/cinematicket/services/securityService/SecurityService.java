package com.example.cinematicket.services.securityService;

import com.example.cinematicket.entities.Comment;
import com.example.cinematicket.entities.Invoice;
import com.example.cinematicket.entities.SeatReservation;
import com.example.cinematicket.entities.Ticket;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.repositories.CommentRepository;
import com.example.cinematicket.repositories.InvoiceRepository;
import com.example.cinematicket.repositories.SeatReservationRepository;
import com.example.cinematicket.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class SecurityService {
    CommentRepository commentRepository;
    TicketRepository ticketRepository;
    InvoiceRepository invoiceRepository;
    SeatReservationRepository seatReservationRepository;

    public boolean isCommentOwner(Long commentId, Authentication authentication) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            return false;
        }

        String currentUsername = authentication.getName();
        return comment.getUser().getEmail().equals(currentUsername);
    }

    public boolean isTicketOwner(Long ticketId, Authentication authentication) {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if (ticket == null) {
            return false;
        }

        String currentUsername = authentication.getName();
        return ticket.getInvoice().getUser().getEmail().equals(currentUsername);
    }

    public boolean isSeatReservationOwner(Long cinemaSeatId, Long scheduleId,LocalDateTime expiryTime, Long userId) {
        SeatReservation seatReservation = seatReservationRepository.findByCinemaSeatIdAndScheduleId(cinemaSeatId, scheduleId, expiryTime)
                .orElseThrow(() -> new AppException(ErrorCode.CINEMA_SEAT_NOT_EXISTED));

        return seatReservation.getUserId().equals(userId);
    }

    public boolean isInvoiceOwner(Long invoiceId, Authentication authentication) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElse(null);
        if (invoice == null) {
            return false;
        }

        String currentUsername = authentication.getName();
        return invoice.getUser().getEmail().equals(currentUsername);
    }

    public boolean isSeatReservationOwner(Set<Long> seatIds, String userId) {
        return seatReservationRepository.existsByIdInAndUserId(seatIds, userId);
    }
}
