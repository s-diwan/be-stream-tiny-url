package com.tinyurl.tinyserver.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tinyurl.tinyserver.model.UrlMapper;

public interface UrlMapperRepository extends JpaRepository<UrlMapper, Integer>{
	UrlMapper findByShortUrl(String url);
}
