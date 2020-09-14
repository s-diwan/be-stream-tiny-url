package com.tinyurl.tinyserver.service;


public interface TinyUrlService {
	public String createTinyUrl(String longUrl);
	public String getLongUrl(String shortUrl,String id);
	public String createTinyUrlForGroup(String longUrl,String groupName);
	public String createTinyUrlForCard(String longUrl,String userName);
}
