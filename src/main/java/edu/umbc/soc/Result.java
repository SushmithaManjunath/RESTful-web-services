package edu.umbc.soc;

import java.io.Serializable;

public class Result implements Serializable	{
	
	private static final long serialVersionUID = -2717540259971937998L;
	
	private String id;
	private String result;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

}
