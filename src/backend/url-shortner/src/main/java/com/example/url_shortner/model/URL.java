package com.example.url_shortner.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Entity
public class URL {
    @Id
    private String url;
    @Column(unique = true, nullable = false)
    private String shortUrl;
    private LocalDateTime createdOn;

    public URL(String url, String shortUrl, LocalDateTime createdOn) {
        this.url = url;
        this.shortUrl = shortUrl;
        this.createdOn = createdOn;
    }

    public URL() {

    }

    private String getURL() {
        return url;
    }

    private String getShortURL() {
        return shortUrl;
    }

    private LocalDateTime getCreatedOn() {
        return createdOn;
    }
}
