package io.github.arturtcs.springsecurity.controller;

import io.github.arturtcs.springsecurity.dto.FeedDTO;
import io.github.arturtcs.springsecurity.dto.TweetRequestDTO;
import io.github.arturtcs.springsecurity.entities.Tweet;
import io.github.arturtcs.springsecurity.service.TweetService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping
    public ResponseEntity<Void> newTweet(
            @RequestBody TweetRequestDTO tweet,
            JwtAuthenticationToken token) {

        tweetService.newTweet(tweet, token);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable("id") Long tweetId, JwtAuthenticationToken token) throws BadRequestException {

        tweetService.deleteTweet(tweetId, token);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/feed")
    public ResponseEntity<FeedDTO> feed(@RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        var tweets = tweetService.findAll(page, pageSize);
        return ResponseEntity.ok().body(new FeedDTO(tweets.getContent(), page, pageSize, tweets.getTotalPages(), tweets.getTotalElements()));
    }
}
