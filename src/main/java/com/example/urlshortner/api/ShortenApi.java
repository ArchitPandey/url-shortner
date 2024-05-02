package com.example.urlshortner.api;

import com.example.urlshortner.api.request.ShortenUrlRequest;
import com.example.urlshortner.api.response.ShortenUrlResponse;
import com.example.urlshortner.service.UrlShortnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ShortenApi {

    private UrlShortnerService urlShortnerService;
    public ShortenApi(UrlShortnerService urlShortnerService) {
        this.urlShortnerService = urlShortnerService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortenUrlResponse> shorten(@RequestBody ShortenUrlRequest request) {
        String shortUrl = this.urlShortnerService.shorten(request.getLongUrl());
        return new ResponseEntity<ShortenUrlResponse>(new ShortenUrlResponse(shortUrl, "success"), HttpStatus.CREATED);
    }

}
