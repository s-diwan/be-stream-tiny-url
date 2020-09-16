package com.tinyurl.tinyserver.dto;

import lombok.Data;

@Data
public class UpdateCardDto {
	private int id;
	private String url;
    private String title;
    private String description;
    private String cardType;
    private int group_id;
    private int userId;
}
