package com.sumeria.booksDemo.booksDemo.webClients;

import com.fasterxml.jackson.databind.node.TextNode;
import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslHandshakeTimeoutException;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutException;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class TitleToAuthorMapperWebClient {
    private final WebClient client;

    @Autowired
    public TitleToAuthorMapperWebClient(
            @Value("${booksDemo.titleToAuthorMapperServiceUrl}")
            String baseUrl) {
        // https://www.baeldung.com/spring-webflux-timeout
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        this.client = WebClient
                .builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String getAuthorOfTitle(String title) {
        Random random = new Random();
        int randomId = random.ints(1, 99)
                .findFirst()
                .getAsInt();
        TitleToAuthorResponse response = client
                .get()
                .uri("/users/" + randomId)
                .retrieve()
                .onStatus(errorCode -> errorCode.is4xxClientError(), resp -> {
                    log.error("ClientError {}", resp.statusCode());
                    return Mono.error(new RuntimeException("ClientError"));
                })
                .bodyToMono(TitleToAuthorResponse.class)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))
                .onErrorMap(ReadTimeoutException.class, ex -> new HttpTimeoutException("ReadTimeout"))
                .onErrorReturn(SslHandshakeTimeoutException.class, new TitleToAuthorResponse())
                .doOnError(WriteTimeoutException.class, ex -> log.error("WriteTimeout"))
                .block();

        return response.getFirstName() + " " + response.getLastName();
    }
}
