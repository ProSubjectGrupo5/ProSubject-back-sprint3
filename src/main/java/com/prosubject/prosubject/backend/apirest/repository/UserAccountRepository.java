package com.prosubject.prosubject.backend.apirest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prosubject.prosubject.backend.apirest.model.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>  {
	
	@Query("select u from useraccounts u where u.username=?1 AND u.password=?2")
	Optional<UserAccount> cuentaLogueada(String usuario, String password);
	
	


}
