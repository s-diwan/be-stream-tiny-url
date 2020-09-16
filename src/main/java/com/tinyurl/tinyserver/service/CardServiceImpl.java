package com.tinyurl.tinyserver.service;


import com.tinyurl.tinyserver.dao.CardRepository;
import com.tinyurl.tinyserver.dao.GroupRepository;
import com.tinyurl.tinyserver.dto.DeleteCardDto;
import com.tinyurl.tinyserver.dto.UpdateCardDto;
import com.tinyurl.tinyserver.model.Card;
import com.tinyurl.tinyserver.model.Group;
import com.tinyurl.tinyserver.model.UrlMapper;
import com.tinyurl.tinyserver.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements  CardService{

    @Autowired
    CardRepository cardRepository;
    
    @Autowired
    TinyUrlService tinyUrlService;
    
    @Autowired
    GroupRepository groupRepository;
    
    @Override
    public void createCardInGroup(Card card, int id,User user) {
    	String groupName = groupRepository.findById(id).get().getGroupName();
    	UrlMapper urlMapper = new UrlMapper();
    	urlMapper.setLongUrl(card.getUrl());
    	urlMapper.setShortUrl(tinyUrlService.createTinyUrlForGroup(card.getUrl(),groupName));
    	urlMapper.setGroup_id(id);
    	urlMapper.setUser_id(user.getId());
    	card.setUrlMapper(urlMapper);
    	card.setGroup_id(id);
    	card.setUserId(user.getId());
        cardRepository.save(card);
    }

	@Override
	public List<Card> getAllCards() {
		// TODO Auto-generated method stub
		return cardRepository.findAll();
	}

	@Override
	public void createCardInUser(Card card, User user) {
		// TODO Auto-generated method stub
		UrlMapper urlMapper = new UrlMapper();
    	urlMapper.setLongUrl(card.getUrl());
    	urlMapper.setShortUrl(tinyUrlService.createTinyUrlForCard(card.getUrl(),user.getUserName()));
    	urlMapper.setUser_id(user.getId());
    	card.setUserId(user.getId());
    	card.setUrlMapper(urlMapper);
        cardRepository.save(card);
	}

	@Override
	public List<Card> getMyCards(User user) {
		// TODO Auto-generated method stub
		return cardRepository.findByUserId(user.getId());
	}

	@Override
	public void upadteCardInUser(UpdateCardDto card, User user) {
		// TODO Auto-generated method stub
		Optional<Card> tempCard = cardRepository.findById(card.getId());
		if(tempCard.isPresent()){
			tempCard.get().setTitle(card.getTitle());
			tempCard.get().setDescription(card.getDescription());
			tempCard.get().setCardType(card.getCardType());
			cardRepository.save(tempCard.get());
		}
	}

	@Override
	public void deleteCardInUser(DeleteCardDto card, User user) {
		// TODO Auto-generated method stub
		cardRepository.deleteById(card.getId());
	}
}
