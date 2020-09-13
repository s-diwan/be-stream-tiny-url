package com.tinyurl.tinyserver.service;


import com.tinyurl.tinyserver.dao.CardRepository;
import com.tinyurl.tinyserver.model.Card;
import com.tinyurl.tinyserver.model.UrlMapper;
import com.tinyurl.tinyserver.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements  CardService{

    @Autowired
    CardRepository cardRepository;
    
    @Autowired
    TinyUrlService tinyUrlService;
    
    @Override
    public void createCard(Card card, int id,User user) {
    	UrlMapper urlMapper = new UrlMapper();
    	urlMapper.setLongUrl(card.getUrl());
    	urlMapper.setShortUrl(tinyUrlService.createTinyUrl(card.getUrl()));
    	urlMapper.setGroup_id(id);
    	urlMapper.setUser_id(user.getId());
    	card.setUrlMapper(urlMapper);
    	card.setGroup_id(id);
        cardRepository.save(card);
    }

	@Override
	public List<Card> getAllCards() {
		// TODO Auto-generated method stub
		return cardRepository.findAll();
	}
}
