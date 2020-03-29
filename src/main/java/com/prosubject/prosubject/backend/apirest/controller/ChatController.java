package com.prosubject.prosubject.backend.apirest.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.prosubject.prosubject.backend.apirest.model.Respuesta;
import com.prosubject.prosubject.backend.apirest.payload.OutputMessage;

@Controller
public class ChatController {
	
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public OutputMessage send(final Respuesta message) throws Exception {
		
		//return new Respuesta(message.getContenido(), message.getCreacionRespuesta(), message.getUser(), message.getForo());
		
		//final String time = new SimpleDateFormat("HH:mm").format(new Date());
		return new OutputMessage(message.getUser(), message.getContenido(), message.getCreacionRespuesta(),message.getForo());
		
		
	}

}
