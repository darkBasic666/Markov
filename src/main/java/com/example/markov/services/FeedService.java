package com.example.markov.services;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class FeedService {

    public void feed(List<String> words, String text) {
        log.info("Add " +  text );
        List<String> feed = Arrays.asList(text.split(" "));
        words.addAll(feed);
        Collections.sort(words);
    }
}
