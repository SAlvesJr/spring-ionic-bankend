package com.SAlvesjr.cursomc.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fildName;
	private String fildMessage;

	public FieldMessage() {

	}

	public FieldMessage(String name, String message) {
		this.fildName = name;
		this.fildMessage = message;
	}

	public String getFildName() {
		return fildName;
	}

	public void setFildName(String fildName) {
		this.fildName = fildName;
	}

	public String getFildMessage() {
		return fildMessage;
	}

	public void setFildMessage(String fildMessage) {
		this.fildMessage = fildMessage;
	}

}
