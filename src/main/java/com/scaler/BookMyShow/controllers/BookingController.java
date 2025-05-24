package com.scaler.BookMyShow.controllers;

import com.scaler.BookMyShow.dtos.BookMovieTicketRequestDTO;
import com.scaler.BookMyShow.dtos.BookMovieTicketResponseDTO;
import com.scaler.BookMyShow.dtos.SignupResponseDTO;
import com.scaler.BookMyShow.exceptions.SeatNotAvailableException;
import com.scaler.BookMyShow.exceptions.ShowNotFoundException;
import com.scaler.BookMyShow.exceptions.UserAlreadyExistsException;
import com.scaler.BookMyShow.exceptions.UserNotFoundException;
import com.scaler.BookMyShow.models.Booking;
import com.scaler.BookMyShow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;

    public BookMovieTicketResponseDTO bookMovieTicket(BookMovieTicketRequestDTO bookMovieTicketRequestDTO) {
        Booking booking;
        try {
            booking = bookingService.bookMovie(
                    bookMovieTicketRequestDTO.getUserId(),
                    bookMovieTicketRequestDTO.getShowId(),
                    bookMovieTicketRequestDTO.getSeatIds()
            );
        } catch (UserNotFoundException | ShowNotFoundException | SeatNotAvailableException runtimeException) {
            booking = null;
        }
        BookMovieTicketResponseDTO bookMovieTicketResponseDTO = new BookMovieTicketResponseDTO();
        bookMovieTicketResponseDTO.setBooking(booking);
        return bookMovieTicketResponseDTO;
    }
}
