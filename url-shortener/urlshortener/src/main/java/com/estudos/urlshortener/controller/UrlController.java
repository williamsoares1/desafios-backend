package com.estudos.urlshortener.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.urlshortener.dtos.UrlDTO;
import com.estudos.urlshortener.entity.Url;
import com.estudos.urlshortener.repository.UrlRepository;

import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;
import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UrlController {

    @Autowired
    private UrlRepository repository;

    @PostMapping(value = "shorten-url")
    public ResponseEntity<UrlDTO> urlShortener(@RequestBody UrlDTO dto, HttpServletRequest request) {
        String id;

        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (repository.existsById(id));

        repository.save(new Url(id, dto.url(), LocalDateTime.now().plusMinutes(1)));

        var redirectUrl = request.getRequestURL().toString().replace("shorten-url", id);

        return ResponseEntity.ok(UrlDTO.builder().url(redirectUrl).build());
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getMethodName(@PathVariable String id) {
        var url = repository.findById(id);

        if(url.isEmpty()) return ResponseEntity.notFound().build();

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(URI.create(url.get().getFullUrl()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
    

}
