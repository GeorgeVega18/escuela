package mx.gufe.escuela.utils;

import java.io.Serializable;

public class OveralResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String success;
	private String msg;
	
	
	public OveralResponse() {
		super();
	}


	public OveralResponse(String success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}


	public String getSuccess() {
		return success;
	}


	public void setSuccess(String success) {
		this.success = success;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
