package io.github.arturtcs.springsecurity.service.impl;

import io.github.arturtcs.springsecurity.dto.TweetRequestDTO;
import io.github.arturtcs.springsecurity.entities.Role;
import io.github.arturtcs.springsecurity.entities.Tweet;
import io.github.arturtcs.springsecurity.entities.User;
import io.github.arturtcs.springsecurity.repositories.TweetRepository;
import io.github.arturtcs.springsecurity.repositories.UserRepository;
import io.github.arturtcs.springsecurity.service.TweetService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;

    private final UserRepository userRepository;

    public TweetServiceImpl(TweetRepository tweetRepository,
                            UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void newTweet(TweetRequestDTO tweet, JwtAuthenticationToken token) {
        var userLogged = userRepository.findById(UUID.fromString(token.getName()));
        var newTweet = new Tweet();
        newTweet.setUser(userLogged.get());
        newTweet.setContent(tweet.content());
        tweetRepository.save(newTweet);
    }

    @Override
    public void deleteTweet(Long tweetIid, JwtAuthenticationToken token) throws BadRequestException {

        var user = userRepository.findById(UUID.fromString(token.getName()));
        var optTweet = tweetRepository.findById(tweetIid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User tweetOwner = optTweet.getUser();

        var isAdmin = user.get().getRoles()
                .stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));

        if (isAdmin || tweetOwner.getUserId().equals(UUID.fromString(token.getName())) || user.get().getRoles().contains(Role.Values.ADMIN))
            tweetRepository.deleteById(tweetIid);
        else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);


    }
}
