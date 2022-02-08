package com.konnnect.position.dto;


import org.json.simple.JSONObject;

import com.konnnect.common.utilities.GeneralUtils;
import com.konnnect.common.exception.KonnnectException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPlatformDTO {
		
	public String platform;
	public String url;

	
	/*
	 * convert to, and return json
	 */
	public JSONObject toJson() throws Exception
	{
		JSONObject job = new JSONObject();
				
		GeneralUtils.putStringIntoJSONObj(job, "platform", platform, false);
		GeneralUtils.putStringIntoJSONObj(job, "url", url, false);
		
		return job;
	}
	
	
	
	/*
	 * build from json
	 */
	public void fromJson(JSONObject job) throws Exception
	{
		platform   	= GeneralUtils.getStringParamFromJSONObj(job, "platform", false);
		url   	    = GeneralUtils.getStringParamFromJSONObj(job, "url", false);
	}
	
	
	
	/*
	 * verify required data is available
	 */
	public void verify() throws KonnnectException
	{
		if(platform==null)
			throw new KonnnectException("verifying review plaform","platform name is null");
		if(url==null)
			throw new KonnnectException("verifying review plaform","platform url is null");
	}
}
