package io.github.arturtcs.springsecurity.dto;

public record FeedItemDTO(Long tweetId,
                          String content,
                          String username) {
}
