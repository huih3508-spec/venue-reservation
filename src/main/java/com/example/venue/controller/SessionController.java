package com.example.venue.controller;
import com.example.venue.common.Result;
import com.example.venue.entity.Session;
import com.example.venue.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping("list/{venueId}")
    public Result<List<Session>> listByVenue(@PathVariable Long venueId){
        return Result.success(sessionService.listByVenueId(venueId));
    }
}
