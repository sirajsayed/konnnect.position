package com.konnnect.position.dto;


import org.springframework.http.HttpStatus;

import com.konnnect.common.position.PositionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
		
	public ReviewsDTO review_platforms;
	
	public PositionDTO position;
	
	public String message;
	public HttpStatus httpStatus=HttpStatus.OK;
	
}
