package com.tinyurl.tinyserver.dto;

import lombok.Data;

@Data
public class DeleteCardDto {
	private int id;
    private int group_id;
    private int userId;
}
