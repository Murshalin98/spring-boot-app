package com.several.vnoticeboard.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RegistrationForm {

	@NotEmpty
	private String boardName;
	
	@Size(min=4, max=100)
	private String password;

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
