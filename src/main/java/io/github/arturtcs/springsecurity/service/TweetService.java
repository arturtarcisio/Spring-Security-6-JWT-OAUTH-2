package io.github.arturtcs.springsecurity.service;

import io.github.arturtcs.springsecurity.dto.TweetRequestDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface TweetService {

    void newTweet(TweetRequestDTO tweet, JwtAuthenticationToken token);

    void deleteTweet(Long tweetId, JwtAuthenticationToken token) throws BadRequestException;
}
