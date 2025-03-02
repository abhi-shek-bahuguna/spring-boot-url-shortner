package com.example.url_shortner.service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.example.url_shortner.model.URL;
import com.example.url_shortner.repository.URLRepo;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class URLService {
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final int ID_LENGTH = 6;
    private static final String BASE_URL = "tinyurl";
    URLRepo urlRepo;

    URLService(URLRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    public String getURL(String shortUrl) {
        if (urlRepo.urlExists(shortUrl)) {
            return urlRepo.getUrl(shortUrl);
        }
        return "URL does not exists";
    }

    public String createShortURL(String url) {
        String shortURL = BASE_URL + NanoIdUtils.randomNanoId(new SecureRandom(), ALPHABET, ID_LENGTH) + ".com";
        LocalDateTime createdOn = LocalDateTime.now();
        urlRepo.save(new URL(url, shortURL, createdOn));
        return shortURL;
    }
}
