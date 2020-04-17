package com.prosubject.prosubject.backend.apirest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.prosubject.prosubject.backend.apirest.controller.ProfesorController;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.service.ProfesorService;


@SpringBootApplication
public class ProsubjectBackendApirestApplication {
	
	@Autowired
	private ProfesorService pS;
	
	public static void main(String[] args) {
		SpringApplication.run(ProsubjectBackendApirestApplication.class, args);
	}
	
	//Se ejecutará una vez al dia, comprueba los profesores que son premium, y si ha pasado un mes, se les quitará el plan premium
	@Scheduled(fixedDelay = 86400000)
	public void comprobarPremium() {
		Date d = new Date();
		Boolean res = Boolean.FALSE;
	
				for(Profesor p : pS.profesoresTarifaPremium()) {
						if(ProfesorController.numeroDiasEntreDosFechas(p.getFechaPagoPremium(),d)>30) {
							p.setTarifaPremium(res);
							p.setFechaPagoPremium(null);
							this.pS.save(p);
		  }
	  }

	
	}
	
	@Configuration
	@EnableScheduling
	@ConditionalOnProperty(name="scheduling.enable", matchIfMissing=true)
	class SchedulingConfiguration{
		
	}

}
