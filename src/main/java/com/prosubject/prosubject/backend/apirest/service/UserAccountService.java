package com.prosubject.prosubject.backend.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosubject.prosubject.backend.apirest.model.UserAccount;
import com.prosubject.prosubject.backend.apirest.repository.UserAccountRepository;

@Service
public class UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepository;
	
	
	
	public UserAccount findByUserAndPass(String usuario, String password) {
		return this.userAccountRepository.cuentaLogueada(usuario, password).orElse(null);
	}
	
	public List<String> todosUsername() {
		return this.userAccountRepository.todosUsername();
	}
	
	public void delete(UserAccount userAccount) {
		this.userAccountRepository.delete(userAccount);
	}
	
	
}
