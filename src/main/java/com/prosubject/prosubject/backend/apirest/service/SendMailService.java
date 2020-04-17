package com.prosubject.prosubject.backend.apirest.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.google.common.base.Joiner;

@Service
public class SendMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private AlumnoService alumnoService;

	@Autowired
	private ProfesorService profesorService;

	public String senMail(String to, String subject, String body) {

		String res = "Enviado a: " + to + "\nAsunto: " + subject + "\nCuerpo: " + body;

		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setFrom("ProSubject2020.ISPP@gmail.com");
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(body);

		javaMailSender.send(mailMessage);

		return res;

	}

	public String sendAlert(String subject, String body) throws MessagingException {

		String res = "\nAsunto: " + subject + "\nCuerpo: " + body;

		List<String> listEmails = this.alumnoService.emailsAlumno();
		listEmails.addAll(this.profesorService.emailsProfesor());

		String delimitante = ",";

		String to = Joiner.on(delimitante).join(listEmails);

		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);

		helper.setTo(InternetAddress.parse(to));
		helper.setFrom("ProSubject2020.ISPP@gmail.com");
		helper.setSubject(subject);
		helper.setText(body);

		javaMailSender.send(mailMessage);

		return res;

	}

}
