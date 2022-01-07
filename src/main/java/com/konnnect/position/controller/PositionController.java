package com.konnnect.position.controller;


import java.util.UUID;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.konnnect.exception.KonnnectException;
import com.konnnect.position.dto.ResponseDTO;
import com.konnnect.position.dto.ReviewsDTO;
import com.konnnect.position.service.Position;

@RequestMapping("api/v1")
@RestController
public class PositionController {

	
	/*
	 * update user's position
	 */
	@PutMapping("/{usid}/{lat}/{lon}")
	public ResponseEntity<ResponseDTO> update(@PathVariable("usid") UUID usid, 
											  @PathVariable("lat") Double lat, 
											  @PathVariable("lon") Double lon) throws KonnnectException {
				
		try
		{
			// execute service & return a success			
			ResponseDTO res = new Position().update(usid, lat, lon);
			return new ResponseEntity<ResponseDTO>(res, res.httpStatus);
		}
		catch(Exception ex)
		{
			ResponseDTO res = new ResponseDTO();
			res.message = ex.getMessage();
			
			return new ResponseEntity<ResponseDTO>(res, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}		
	/*
	 * get user's position
	 */
	@GetMapping("/{usid}")
	public ResponseEntity<ResponseDTO> get(@PathVariable("usid") UUID usid) throws KonnnectException {
				
		try
		{
			// execute service & return a success			
			ResponseDTO res = new Position().get(usid);
			return new ResponseEntity<ResponseDTO>(res, res.httpStatus);
		}
		catch(Exception ex)
		{
			ResponseDTO res = new ResponseDTO();
			res.message = ex.getMessage();
			
			return new ResponseEntity<ResponseDTO>(res, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}		
}
