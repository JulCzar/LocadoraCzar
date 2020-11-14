package br.czar.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.czar.model.User;
import br.czar.util.SessionStorage;

@Named
@ViewScoped
public class TemplateController implements Serializable {
	private static SessionStorage sessionStorage = SessionStorage.getInstance();
	private static final long serialVersionUID = -5646848465487484984L;
	
	public void logout() {
		sessionStorage.clear();
	}
	
	public User getUser() {
		Object user = sessionStorage.getItem("userData");
		
		System.out.println(user);
		if (user == null)
			return null;
		
		return (User)user;
	}
}
