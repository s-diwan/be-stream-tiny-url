package com.tinyurl.tinyserver.service;


import java.util.List;

import com.tinyurl.tinyserver.model.Card;
import com.tinyurl.tinyserver.model.User;

public interface CardService {
    public void createCardInGroup(Card card, int id,User user);
    public void createCardInUser(Card card, User user);
    public List<Card> getAllCards();
    public List<Card> getMyCards(User user);
}
