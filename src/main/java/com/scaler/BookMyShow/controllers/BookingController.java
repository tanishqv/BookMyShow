package com.scaler.BookMyShow.controllers;

import com.scaler.BookMyShow.dtos.BookMovieTicketRequestDTO;
import com.scaler.BookMyShow.dtos.BookMovieTicketResponseDTO;
import com.scaler.BookMyShow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;

    public BookMovieTicketResponseDTO bookMovieTicket(BookMovieTicketRequestDTO bookMovieTicketRequestDTO) {
        return null;
    }
}
