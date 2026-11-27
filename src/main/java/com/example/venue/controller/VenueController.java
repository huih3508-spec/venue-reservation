package com.example.venue.controller;
import com.example.venue.common.Result;
import com.example.venue.entity.Venue;
import com.example.venue.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/venue")
public class VenueController {
    @Autowired
    private VenueService venueService; //私有字段，仅本类使用

    @GetMapping("/list")
    public Result<List<Venue>> listVenue(){  //Venue 单个场馆对象，List<Venue> 多个场馆组成的列表
        return Result.success(venueService.listAllVenue());
    }
}
