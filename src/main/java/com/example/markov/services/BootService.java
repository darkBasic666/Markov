package com.example.markov.services;

import com.example.markov.models.Feed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class BootService {
    Map<String, List<String>> boots = new HashMap<>();

    @Autowired
    FeedService feedService;

    public void addBoot(String name){
        boots.put(name, new ArrayList<String>());
    }

    public void feed(Feed feed){
        if (boots.get(feed.getName()) == null) addBoot(feed.getName());
        feedService.feed(boots.get(feed.getName()), feed.getText());
    }


    public String took(Integer length, String name) {
        String response = "";
        for (int i = 0; i < length; i++) {
            Integer random = new Random().nextInt(boots.get(name).size());
            response += " " + boots.get(name).get(random);
        }
        return response;
    }
}
