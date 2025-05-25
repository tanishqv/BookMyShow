package com.scaler.BookMyShow.controllers;

import com.scaler.BookMyShow.dtos.CreateShowRequestDTO;
import com.scaler.BookMyShow.dtos.CreateShowResponseDTO;
import com.scaler.BookMyShow.exceptions.*;
import com.scaler.BookMyShow.models.Show;
import com.scaler.BookMyShow.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ShowController {

    @Autowired
    private ShowService showService;

    public CreateShowResponseDTO createShow(CreateShowRequestDTO createShowRequestDTO) {
        Show show;
        try {
            show = showService.createShow(
                    createShowRequestDTO.getMovieId(),
                    createShowRequestDTO.getStartTime(),
                    createShowRequestDTO.getEndTime(),
                    createShowRequestDTO.getScreenId(),
                    createShowRequestDTO.getFeatures()
            );
        } catch (
            MovieNotFoundException |
            ScreenNotFoundException |
            MovieFeatureNotSupportedException |
            ScreenFeatureNotSupportedException |
            ScreenIncompatibilityException runtimeException
        ) {
            show = null;
        }
        CreateShowResponseDTO createShowResponseDTO = new CreateShowResponseDTO();
        createShowResponseDTO.setShow(show);
        return createShowResponseDTO;
    }
}
