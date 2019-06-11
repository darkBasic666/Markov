package com.example.markov.services;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Service
public class FeedService {

    private Integer keySize = 3;
    public void feed(Map<String, List<String>> dict, String text) {
        log.info("Add " +  text );
        List<String> words = Arrays.asList(text.split(" "));

        for (int i = 0; i < (words.size() - keySize); ++i) {
            StringBuilder key = new StringBuilder(words.get(i));
            for (int j = i + 1; j < i + keySize; ++j) {
                key.append(' ').append(words.get(j));
            }
            String value = (i + keySize < words.size()) ? words.get(i + keySize) : "";
            if (!dict.containsKey(key.toString())) {
                List<String> list = new ArrayList<>();
                list.add(value);
                dict.put(key.toString(), list);
            } else {
                dict.get(key.toString()).add(value);
            }
        }

    }
}
