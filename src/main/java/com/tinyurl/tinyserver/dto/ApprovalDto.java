package com.tinyurl.tinyserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApprovalDto {
	private int id;
	private int cardId;
	private String title;
	private String description;
	private String cardType;
	private int requestedBy;
	private String status;
}
