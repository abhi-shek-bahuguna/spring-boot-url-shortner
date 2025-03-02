package com.example.url_shortner.repository;

import com.example.url_shortner.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepo extends JpaRepository<URL,String> {
    @Query("SELECT COUNT(u) > 0 FROM URL u WHERE u.shortUrl = :shortUrl")
    boolean urlExists(@Param("shortUrl") String shortUrl);

    @Query("SELECT u.url FROM URL u WHERE u.shortUrl = :shortUrl")
    String getUrl(@Param("shortUrl") String email);
}
