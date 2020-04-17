package com.prosubject.prosubject.backend.apirest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prosubject.prosubject.backend.apirest.service.SendMailService;

@RestController
@RequestMapping("/api/mail")
@CrossOrigin(origins = { "http://localhost:4200", "https://prosubject-v2.herokuapp.com" })
public class SendMailController {

	@Autowired
	private SendMailService sendMailService;

	@PostMapping("/enviar")
	public ResponseEntity<?> enviarEmail(@RequestParam("to") String to, @RequestParam("subject") String subject,
			@RequestParam("body") String body) {

		String res;
		Map<String, Object> response = new HashMap<String, Object>();

		try {
			res = this.sendMailService.senMail(to, subject, body);
		} catch (Exception e) {
			response.put("mensaje", "Error al mandar el email");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>(res, HttpStatus.OK);

	}

	@PostMapping("/enviarBroadcast")
	public ResponseEntity<?> sendAlert(@RequestParam("subject") String subject, @RequestParam("body") String body) {

		String res;
		Map<String, Object> response = new HashMap<String, Object>();

		try {
			res = this.sendMailService.sendAlert(subject, body);
		} catch (Exception e) {
			response.put("mensaje", "Error al mandar el email");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>(res, HttpStatus.OK);

	}

}
