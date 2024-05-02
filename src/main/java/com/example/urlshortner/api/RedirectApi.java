package com.example.urlshortner.api;

import com.example.urlshortner.service.UrlShortnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

import java.util.Objects;

@RestController
@RequestMapping("/r")
@Slf4j
public class RedirectApi {

    private UrlShortnerService urlShortnerService;

    public RedirectApi(UrlShortnerService urlShortnerService) {
        this.urlShortnerService = urlShortnerService;
    }

    @GetMapping(value="/{shortUrl}")
    public ResponseEntity<String> redirect(@PathVariable("shortUrl") String shortUrl) {
        String longUrl = this.urlShortnerService.findLongUrl(shortUrl);

        if (Objects.nonNull(longUrl)) {
            log.info("redirecting {} to {}", shortUrl, longUrl);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.LOCATION, longUrl);
            return new ResponseEntity("success", headers, HttpStatus.TEMPORARY_REDIRECT);
        } else {
            return new ResponseEntity("not found", HttpStatus.BAD_REQUEST);
        }
    }

}
