package com.tinyurl.tinyserver.service;


public interface TinyUrlService {
	public String createTinyUrl(String longUrl);
	public String getLongUrl(String shortUrl,String id);
}
