package br.czar.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.czar.model.Privilege;
import br.czar.model.User;
import br.czar.util.SessionStorage;

@Named
@RequestScoped
public class SessionController {
	public static final SessionStorage sessionStorage = SessionStorage.getInstance();
	
	public User getUserdata() {
		Object obj = sessionStorage.getItem("userData");
		if (obj == null)
			obj = new User();
		
		return (User)obj;
	}
	
	public boolean isLoggedUserAdmin() {
		return Privilege.ADMIN.equals(getUserdata().getPrivilege());
	}
}
