package com.example.demo.dto;
/*
 * @author gunapal.p
 */
import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseDto implements Serializable{

	
	private static final long serialVersionUID = -3103934031359382927L;
		
	private boolean error;
		
	private transient Object data;


	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	
}
