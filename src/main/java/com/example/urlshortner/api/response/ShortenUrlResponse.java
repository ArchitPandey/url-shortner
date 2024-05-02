package com.example.urlshortner.api.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ShortenUrlResponse {

    private String shortUrl;

    private String status;

}
