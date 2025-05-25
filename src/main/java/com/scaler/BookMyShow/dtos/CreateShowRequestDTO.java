package com.scaler.BookMyShow.dtos;

import com.scaler.BookMyShow.models.Feature;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreateShowRequestDTO {
    private Long movieId;
    private Date startTime;
    private Date endTime;
    private Long screenId;
    private List<Feature> features;

}
