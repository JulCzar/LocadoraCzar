package br.czar.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.czar.dao.UserDAO;
import br.czar.model.User;
import br.czar.util.SessionStorage;
import br.czar.util.Utils;

@Named
@ViewScoped
public class LoginController implements Serializable {
	private static SessionStorage sessionStorage = SessionStorage.getInstance();
	private static final long serialVersionUID = 2517582323873369098L;
	private User user;
	
	public void login() {
		User u = UserDAO.validateLogin(getUser());
		
		if (u != null) {
			sessionStorage.setItem("userData", u);
			Utils.redirect("index.xhtml");
		} else
			Utils.addErrorMessage("Usuario ou Senha Incorreto");
	}

	public User getUser() {
		if (user == null)
			user = new User();
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
