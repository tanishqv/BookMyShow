package com.scaler.BookMyShow.services;

import com.scaler.BookMyShow.exceptions.SeatNotAvailableException;
import com.scaler.BookMyShow.exceptions.ShowNotFoundException;
import com.scaler.BookMyShow.exceptions.UserNotFoundException;
import com.scaler.BookMyShow.models.*;
import com.scaler.BookMyShow.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private ShowSeatTypeRepository showSeatTypeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PriceCalculationService priceCalculationService;
    @Autowired
    private BookingRepository bookingRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, Long showId, List<Long> seatIds) throws UserNotFoundException, ShowNotFoundException, SeatNotAvailableException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found!"));
        Show show = showRepository.findById(showId).orElseThrow(() -> new ShowNotFoundException("Show with id " + showId + " not found!"));
        List<ShowSeat> showSeats = showSeatRepository.findByShow_IdAndSeat_IdIn(showId, seatIds);
        for (ShowSeat showSeat: showSeats) {
            if (!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new SeatNotAvailableException("Seat already booked");
            }
        }

        // Update seat status for booking
        for (ShowSeat showSeat: showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeatRepository.save(showSeat);
        }

        // create booking
        Booking booking = new Booking();
        booking.setBookedBy(user);
        booking.setShowSeats(showSeats);
        booking.setAmount(priceCalculationService.getPrice(showSeats));

        booking.setBookingStatus(BookingStatus.PENDING);
        // call payment service
        // make payments
        // update booking status

        return bookingRepository.save(booking);
    }
}
