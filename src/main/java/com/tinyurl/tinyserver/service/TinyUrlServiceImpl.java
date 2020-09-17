package com.tinyurl.tinyserver.service;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.tinyurl.tinyserver.dao.UrlMapperRepository;
import com.tinyurl.tinyserver.dto.TinyUrlInput;
import com.tinyurl.tinyserver.model.UrlMapper;

@Service
public class TinyUrlServiceImpl implements TinyUrlService {
	
	@Autowired
	UrlMapperRepository urlMapperRepository;
	
	@Override
	public String createTinyUrl(TinyUrlInput input) {
		// TODO Auto-generated method stub
		 System.out.println(input.getLongUrl());
		 UrlMapper urlMapper = new UrlMapper();
         urlMapper.setLongUrl(input.getLongUrl());
         
         Calendar calendar = Calendar.getInstance();
         calendar.add(Calendar.MINUTE, input.getTime());
         TimeZone time_zone 
         = TimeZone.getTimeZone("GMT");
         calendar.setTimeZone(time_zone);
         urlMapper.setTtl(calendar);
         System.out.println(calendar.getTimeZone());
         
		 String id = Hashing.murmur3_32().hashString(input.getLongUrl()+System.currentTimeMillis(), StandardCharsets.UTF_8).toString();
		 String shortUrl = "http://tinyurlserver-env.eba-nt8f26gy.us-east-2.elasticbeanstalk.com/"+"tiny/"+id;
         System.out.println("URL Id generated: "+ id);
         urlMapper.setShortUrl(shortUrl);
         urlMapperRepository.save(urlMapper);
         return shortUrl;
	}

	@Override
	public String getLongUrl(String shortUrl, String id) {
		// TODO Auto-generated method stub
		UrlMapper urlMapper=urlMapperRepository.findByShortUrl(shortUrl);
		return urlMapper.getLongUrl();
	}

	@Override
	public String createTinyUrlForGroup(String longUrl,String groupName) {
		// TODO Auto-generated method stub
		String id = Hashing.murmur3_32().hashString(longUrl+System.currentTimeMillis(), StandardCharsets.UTF_8).toString();
		String shortUrl = "http://tinyurlserver-env.eba-nt8f26gy.us-east-2.elasticbeanstalk.com"+"/grouptiny/"+groupName+"/"+id;
        System.out.println("URL Id generated: "+ id);
		return shortUrl;
	}

	@Override
	public String createTinyUrlForCard(String longUrl, String userName) {
		// TODO Auto-generated method stub
		String id = Hashing.murmur3_32().hashString(longUrl+System.currentTimeMillis(), StandardCharsets.UTF_8).toString();
		String shortUrl = "http://tinyurlserver-env.eba-nt8f26gy.us-east-2.elasticbeanstalk.com"+"/usertiny/"+userName+"/"+id;
        System.out.println("URL Id generated: "+ id);
		return shortUrl;
	}

	@Override
	public String getAnonymousLongUrl(String shortUrl, String id) {
		// TODO Auto-generated method stub
		UrlMapper urlMapper=urlMapperRepository.findByShortUrl(shortUrl);
		
		Calendar currentCalendar = Calendar.getInstance();
		 TimeZone time_zone 
         = TimeZone.getTimeZone("GMT");
		 currentCalendar.setTimeZone(time_zone);
		Calendar dbCalendar = urlMapper.getTtl();
		System.out.println("Current"+currentCalendar.getTime());
		System.out.println("DB"+dbCalendar.getTimeZone());
		System.out.println("DB"+dbCalendar.getTime());
		
		
		int i = dbCalendar.compareTo(currentCalendar);
		System.out.println(i);
		if(i==1 || i==0){
			return urlMapper.getLongUrl();
		}
		else{
			return "Url Expired";
		}
		
	}

}
