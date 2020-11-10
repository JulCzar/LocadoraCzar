package br.czar.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.czar.dao.UserDAO;
import br.czar.model.User;
import br.czar.util.Utils;

@Named
@ViewScoped
public class UserController extends Controller<User> implements Serializable {

	private static final long serialVersionUID = 3965057035659275669L;
	
	public UserController() {
		super(new UserDAO());
	}
	
	public void login() {
		User u = UserDAO.validateLogin(entity);
		
		if (u != null)
			Utils.redirect("/LocadoraCzar/faces/filmes.xhtml?faces-redirect=true");
		else
			Utils.addErrorMessage("Usuario ou Senha Incorreto");
	}

	@Override
	public User getEntity() {
		if (this.entity == null)
			this.entity = new User();
		
		return this.entity;
	}

}