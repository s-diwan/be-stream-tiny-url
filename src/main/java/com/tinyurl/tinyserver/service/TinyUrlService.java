package com.tinyurl.tinyserver.service;

import com.tinyurl.tinyserver.dto.TinyUrlInput;

public interface TinyUrlService {
	public String createTinyUrl(TinyUrlInput input);
	public String getLongUrl(String shortUrl,String id);
	public String getAnonymousLongUrl(String shortUrl,String id);
	public String createTinyUrlForGroup(String longUrl,String groupName);
	public String createTinyUrlForCard(String longUrl,String userName);
}
