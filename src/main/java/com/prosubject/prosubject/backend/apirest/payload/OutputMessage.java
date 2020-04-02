package com.prosubject.prosubject.backend.apirest.payload;

import java.util.Date;

import com.prosubject.prosubject.backend.apirest.model.Foro;
import com.prosubject.prosubject.backend.apirest.model.UserAccount;

public class OutputMessage {

	    private UserAccount user;
	    private String contenido;
	    private Date creacionRespuesta;
	    private Foro foro;

	    public OutputMessage(final UserAccount user, final String contenido, final Date creacionRespuesta, final Foro foro) {

	        this.user = user;
	        this.contenido = contenido;
	        this.creacionRespuesta = creacionRespuesta;
	        this.foro = foro;
	    }

		public UserAccount getUser() {
			return user;
		}

		public void setUser(UserAccount user) {
			this.user = user;
		}

		public String getContenido() {
			return contenido;
		}

		public void setContenido(String contenido) {
			this.contenido = contenido;
		}

		public Date getCreacionRespuesta() {
			return creacionRespuesta;
		}

		public void setCreacionRespuesta(Date creacionRespuesta) {
			this.creacionRespuesta = creacionRespuesta;
		}

		public Foro getForo() {
			return foro;
		}

		public void setForo(Foro foro) {
			this.foro = foro;
		}

		
	    

}

