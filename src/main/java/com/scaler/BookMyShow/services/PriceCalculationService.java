package com.scaler.BookMyShow.services;

import com.scaler.BookMyShow.models.Show;
import com.scaler.BookMyShow.models.ShowSeat;
import com.scaler.BookMyShow.models.ShowSeatType;
import com.scaler.BookMyShow.repositories.ShowSeatTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculationService {
    @Autowired
    private ShowSeatTypeRepository showSeatTypeRepository;

    public Double getPrice(List<ShowSeat> showSeats) {
        if (showSeats.isEmpty()) {
            return 0D;
        }
        Show show = showSeats.get(0).getShow();
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findByShow_Id(show.getId());
        Double amount = 0D;
        for (ShowSeat showSeat: showSeats) {
            for (ShowSeatType showSeatType: showSeatTypes) {
                if (showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }
        return amount;
    }
}
