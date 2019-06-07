package com.example.markov.controllers;

import com.example.markov.models.Feed;
import com.example.markov.services.BootService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping()
public class MarkovController {
    @Autowired
    BootService bootService;

    @PostMapping("/feed")
    public void feed(@RequestBody Feed feed) {
        bootService.feed(feed);
    }

    @GetMapping("/took")
    public String took(@RequestParam(required = false, defaultValue = "10") Integer length,@RequestParam String name ) {
        return bootService.took(length, name);
    }
}
