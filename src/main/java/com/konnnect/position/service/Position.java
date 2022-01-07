package com.konnnect.position.service;

import java.sql.Connection;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.konnnect.exception.KonnnectException;
import com.konnnect.position.dto.ResponseDTO;
import com.konnnect.position.dto.ReviewsDTO;
import com.konnnect.profile.ProfileAttributes;
import com.konnnect.profile.ProfileDBM;
import com.konnnect.profile.ProfileDTO;
import com.konnnect.common.GeneralUtils;
import com.konnnect.common.position.PositionDBM;
import com.konnnect.common.position.PositionDTO;
import com.konnnect.database.TransactionManager;

@Service
public class Position extends TransactionManager  {
	
	private Logger logger = LoggerFactory.getLogger(Position.class);
	
	
	
	
	/*
	 * update the profile location
	 */
	public ResponseDTO update(UUID id, Double lat, Double lon) throws Exception
	{
		String m = this.getClass().getSimpleName()+"."+(Thread.currentThread().getStackTrace())[1].getMethodName();
        logger.info("ENT {} ", m);				


        PositionDBM    dbm  = new PositionDBM();
        Connection    conn = null;
		
		try
		{			
			
	        conn   = connection();
			
			// persist the 'definition'
	        dbm.update(conn, id, lat, lon);			
			
			// persist entire transaction for this service call
			commit(conn);
			close(conn);
			
			
			// respond with the complete definition
			ResponseDTO res = new ResponseDTO();
			res.message  = "position updated successfully ";
			
			return res;

			
			
		}
		catch(Exception ex)
		{
			return failedResponse(conn, ex.getMessage());									
		}
	}	

	
	/*
	 * update the profile location
	 */
	public ResponseDTO get(UUID id) throws Exception
	{
		String m = this.getClass().getSimpleName()+"."+(Thread.currentThread().getStackTrace())[1].getMethodName();
        logger.info("ENT {} ", m);				


        PositionDBM    dbm  = new PositionDBM();
        Connection    conn  = null;
		
		try
		{			
			
	        conn   = connection();
			
			// persist the 'definition'
	        PositionDTO pos = dbm.get(conn, id);			
			
			// persist entire transaction for this service call
			close(conn);
			
			
			// respond with the complete definition
			ResponseDTO res = new ResponseDTO();
			res.position = pos;
			res.message  = "position fetched successfully ";
			
			return res;

			
			
		}
		catch(Exception ex)
		{
			return failedResponse(conn, ex.getMessage());									
		}
	}	
	
	
	/* create a failed response object and return
	 */	
	private ResponseDTO failedResponse(Connection conn, String msg) throws Exception
	{
		String m = this.getClass().getSimpleName()+"."+(Thread.currentThread().getStackTrace())[1].getMethodName();
        logger.info("ENT {} ", m);
        
        conn.close();
		ResponseDTO res = new ResponseDTO();
		res.message = msg;
		res.httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;

		return res;
		
	}
}
