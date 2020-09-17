 package com.tinyurl.tinyserver.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "card")
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO , generator="card_sequence" )
    private int id;
    private String url;
    private String title;
    private String description;
    private String cardType;
    
    @Column(name = "group_id")
    private int group_id;
    private int userId;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "urlmap_id")
    private UrlMapper urlMapper;
}
