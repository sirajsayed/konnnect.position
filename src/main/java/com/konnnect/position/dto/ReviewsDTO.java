package com.konnnect.position.dto;


import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.konnnect.common.GeneralUtils;
import com.konnnect.exception.KonnnectException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsDTO {
		
	public List<ReviewPlatformDTO> platforms;

	
	/*
	 * convert to, and return json
	 */
	@SuppressWarnings("unchecked")
	public JSONArray toJson() throws Exception
	{
		JSONArray  jarr = new JSONArray();
				
		for (ReviewPlatformDTO platform : platforms)
			jarr.add(platform.toJson());
				
		return jarr;
	}
	
	
	
	/*
	 * build from json
	 */
	public void fromJson(JSONArray platforms) throws Exception
	{
		this.platforms = new ArrayList<ReviewPlatformDTO>();
				
		for (Object obj : platforms)
		{
			JSONObject json = (JSONObject)obj;
			ReviewPlatformDTO platform = new ReviewPlatformDTO();
			platform.platform = GeneralUtils.getStringParamFromJSONObj(json, "platform", true);
			platform.url      = GeneralUtils.getStringParamFromJSONObj(json, "url", true);
			
			this.platforms.add(platform);
		}
	}
	
	
	
	/*
	 * verify required data is available
	 */
	public void verify() throws KonnnectException
	{
		if (platforms == null)
			throw new KonnnectException("verifying review platforms","platforms is null");
	}
}
