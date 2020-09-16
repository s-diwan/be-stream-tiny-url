package com.tinyurl.tinyserver.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tinyurl.tinyserver.service.TinyUrlService;



@RestController
public class TinyUrlController {
	
	@Autowired
	TinyUrlService tinyUrlService;
	
	@GetMapping("/tiny/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void getLongUrl(HttpServletResponse httpServletResponse,@PathVariable("id") String id) throws IOException{
	
		String shortUrl = "http://tinyurlserver-env.eba-nt8f26gy.us-east-2.elasticbeanstalk.com/tiny/"+id;
		httpServletResponse.sendRedirect(tinyUrlService.getLongUrl(shortUrl, id));
		//return tinyUrlService.getLongUrl(shortUrl, id);
	}
	
	@GetMapping("/grouptiny/{groupName}/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void getLongUrlGroup(HttpServletResponse httpServletResponse,@PathVariable("id") String id,@PathVariable("groupName") String groupName) throws IOException{
		String shortUrl = "http://tinyurlserver-env.eba-nt8f26gy.us-east-2.elasticbeanstalk.com/grouptiny/"+groupName+"/"+id;
		httpServletResponse.sendRedirect(tinyUrlService.getLongUrl(shortUrl, id));
		//return tinyUrlService.getLongUrl(shortUrl, id);
	}
	
	@GetMapping("/usertiny/{userName}/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void getLongUrlCard(HttpServletResponse httpServletResponse,@PathVariable("id") String id,@PathVariable("userName") String userName) throws IOException{
		String shortUrl = "http://tinyurlserver-env.eba-nt8f26gy.us-east-2.elasticbeanstalk.com/usertiny/"+userName+"/"+id;
		httpServletResponse.sendRedirect(tinyUrlService.getLongUrl(shortUrl, id));
		//return tinyUrlService.getLongUrl(shortUrl, id);
	}
	
	@PostMapping("/createTinyUrl")
	@ResponseStatus(HttpStatus.OK)
	public String createTinyUrl(@RequestBody String longUrl){
		 UrlValidator urlValidator = new UrlValidator(  new String[]{"http", "https"});
		 if(urlValidator.isValid(longUrl)){
			 return tinyUrlService.createTinyUrl(longUrl); 
		 }
		throw new RuntimeException("URL Invalid"+ longUrl);
	}
}
