package com.dws.albums.client;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.dws.albums.exception.ExternalServiceException;
import com.dws.albums.model.dto.AlbumDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AlbumsApiClient {

    private final WebClient albumsWebClient;
    private final String ALBUMS_ENDPOINT = "/albums";

    public List<AlbumDto> fetchAlbums() {
        return albumsWebClient.get()
                .uri(ALBUMS_ENDPOINT)
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        response -> Mono.error(new ExternalServiceException("Error calling Albums API")))
                .bodyToFlux(AlbumDto.class)
                .collectList()
                .block();
    }
    
    public AlbumDto fetchAlbum(String id) {
        return albumsWebClient.get()
                .uri(String.format("%s/%s", ALBUMS_ENDPOINT, id))
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        response -> Mono.error(new ExternalServiceException("Error calling Albums API")))
                .bodyToFlux(AlbumDto.class)
                .blockFirst();
    }
}
