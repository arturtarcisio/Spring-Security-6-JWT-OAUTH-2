package io.github.arturtcs.springsecurity.service;

import io.github.arturtcs.springsecurity.dto.FeedItemDTO;
import io.github.arturtcs.springsecurity.dto.TweetRequestDTO;
import io.github.arturtcs.springsecurity.entities.Tweet;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public interface TweetService {

    void newTweet(TweetRequestDTO tweet, JwtAuthenticationToken token);

    void deleteTweet(Long tweetId, JwtAuthenticationToken token) throws BadRequestException;

    Page<FeedItemDTO> findAll(int page, int pageSize);
}
