package com.tinyurl.tinyserver.service;


import com.tinyurl.tinyserver.dao.CardRepository;
import com.tinyurl.tinyserver.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements  CardService{

    @Autowired
    CardRepository cardRepository;

    @Override
    public void createCard(Card card, int id) {
        card.setGroup_id(id);
        cardRepository.save(card);
    }
}
