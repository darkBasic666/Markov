package com.example.markov.services;

import com.example.markov.models.Feed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class BootService {

    private static Random r = new Random();

    Map<String, Map<String, List<String>>> bots = new HashMap<>();

    private Integer keySize = 3;

    @Autowired
    FeedService feedService;

    public void addBoot(String name){
        bots.put(name, new HashMap<>());
    }

    public void feed(Feed feed){
        if (bots.get(feed.getName()) == null) addBoot(feed.getName());
        feedService.feed(bots.get(feed.getName()), feed.getText());
    }


    public String took(Integer length, String name) {
        Map<String, List<String>> dict = bots.get(name);
        if (dict == null) throw new wolox.chargebee.bravo.exceptions.BotNotFound("bot with name " + name + " is not found.");
        int n = 0;
        int rn = r.nextInt(dict.size());
        String prefix = (String) dict.keySet().toArray()[rn];
        List<String> output = new ArrayList<>(Arrays.asList(prefix.split(" ")));

        while (true) {

            List<String> suffix = dict.get(prefix);
            if (suffix != null){
                if (suffix.size() == 1) {
                    if (Objects.equals(suffix.get(0), "")) return output.stream().reduce("", (a, b) -> a + " " + b);
                    output.add(suffix.get(0));
                } else {
                    rn = r.nextInt(suffix.size());
                    output.add(suffix.get(rn));
                }

            } else {
                rn = r.nextInt(dict.size());
                List<String> auxPrefix = (List<String>) dict.keySet().toArray()[rn];
                output.addAll(auxPrefix);
            }

            if (output.size() >= length) return output.stream().limit(length).reduce("", (a, b) -> a + " " + b);
            n++;
            prefix = output.stream().skip(n).limit(keySize).reduce("", (a, b) -> a + " " + b).trim();
        }
    }
    
    @Scheduled(cron = "* */90 * * * *")
    public void clean () {
        log.info("Cleaning bots");
        bots = new HashMap<>();
    }
}
