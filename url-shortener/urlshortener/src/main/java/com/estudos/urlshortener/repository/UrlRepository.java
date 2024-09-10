package com.estudos.urlshortener.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.estudos.urlshortener.entity.Url;

@Repository
public interface UrlRepository extends MongoRepository<Url, String> {

}
