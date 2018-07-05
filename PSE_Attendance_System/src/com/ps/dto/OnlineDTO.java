package com.ps.dto;

import java.io.Serializable;

public class OnlineDTO implements Serializable {

	private static final long serialVersionUID = -3618834496750050496L;

	private boolean status;
	private String message;
	private String url;

	public static OnlineDTO initialize() {

		OnlineDTO online = new OnlineDTO();
		online.setStatus(false);
		online.setMessage("ERROR");
		return online;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
