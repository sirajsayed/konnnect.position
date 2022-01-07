package com.konnnect.position.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.konnnect.position.dto.ReviewsDTO;

@FeignClient(value = "registered", url = "http://localhost:9002/")
public interface RegisteredFiegnClient {

	@GetMapping(value = "list_all_registered")
	List<ReviewsDTO> listAllRegistered();
}
