package com.scaler.BookMyShow.services;

import com.scaler.BookMyShow.exceptions.*;
import com.scaler.BookMyShow.models.Feature;
import com.scaler.BookMyShow.models.Movie;
import com.scaler.BookMyShow.models.Screen;
import com.scaler.BookMyShow.models.Show;
import com.scaler.BookMyShow.repositories.MovieRepository;
import com.scaler.BookMyShow.repositories.ScreenRepository;
import com.scaler.BookMyShow.repositories.ShowRepository;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private ShowRepository showRepository;

    public Show createShow(Long movieId, Date startTime, Date endTime, Long screenId, List<Feature> features) throws MovieNotFoundException, ScreenNotFoundException, MovieFeatureNotSupportedException, ScreenFeatureNotSupportedException, ScreenIncompatibilityException {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("movie not available"));
        Screen screen = screenRepository.findById(screenId).orElseThrow(() -> new ScreenNotFoundException("screen does not exist"));

        // screen and movie both should have the requested features
        HashSet<Feature> movieFeatures = new HashSet<>(movie.getFeatures());
        if (!movieFeatures.containsAll(features)) {
            throw new MovieFeatureNotSupportedException("Movie does not support requested features");
        }
        HashSet<Feature> screenFeatures = new HashSet<>(screen.getFeatures());
        if (!screenFeatures.containsAll(features)) {
            throw new ScreenFeatureNotSupportedException("Screen does not support requested features");
        }
        // all screen features should be present in movie features to play the movie
        if (!movieFeatures.containsAll(screen.getFeatures())) {
            throw new ScreenIncompatibilityException("Screen features are not compatible with movie");
        }

        // check for conflicting show on the screen for start and end times
        List<Show> shows = showRepository.findAllConflictingShows(screenId, startTime, endTime);
        if(!shows.isEmpty()) {
            throw new ShowTimeConflictException("showtime conflicts with existing shows");
        }
        Show show = new Show();
        show.setMovie(movie);
        show.setStartTime(startTime);
        show.setEndTime(endTime);
        show.setScreen(screen);
        show.setFeatures(features);
        return showRepository.save(show);
    }

    public void deleteShow(Long showId) {
        Optional<Show> show = showRepository.findById(showId);
        if (show.isPresent()) {
            showRepository.deleteById(showId);
        }
        throw new ShowNotFoundException("Show not found with id=" + showId);
    }
}
