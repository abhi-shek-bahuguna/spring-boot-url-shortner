package com.example.url_shortner.controller;

import com.example.url_shortner.model.URL;
import com.example.url_shortner.service.URLService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class URLController {
    URLService urlService;

    URLController(URLService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/get/{shortUrl}")
    public String getURL(@PathVariable String shortUrl) {
        return urlService.getURL(shortUrl);
    }

    @PostMapping("/post")
    public String postURL(@RequestBody String url) {
        return urlService.createShortURL(url);
    }
}
