package com.prosubject.prosubject.backend.apirest.payload;

import com.prosubject.prosubject.backend.apirest.model.UserAccount;

public class OutputMessage {

	    private UserAccount from;
	    private String text;
	    private String time;

	    public OutputMessage(final UserAccount from, final String text, final String time) {

	        this.from = from;
	        this.text = text;
	        this.time = time;
	    }

	    public String getText() {
	        return text;
	    }

	    public String getTime() {
	        return time;
	    }

	    public UserAccount getFrom() {
	        return from;
	    }
}

