package br.czar.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.czar.dao.UserDAO;
import br.czar.model.Privilege;
import br.czar.model.User;
import br.czar.util.Utils;

@Named
@ViewScoped
public class UserController extends Controller<User> implements Serializable {

	private static final long serialVersionUID = 3965057035659275669L;
	private String search;
	
	public UserController() {
		super(new UserDAO());
		getEntity().setPrivilege(Privilege.valueOf(2));
	}
	
	public void searchByName() {
		setEntityList(null);
		try {
			setEntityList(dao.search(getSearch(), "name"));
		} catch (Exception e) {
			Utils.addErrorMessage("Não foi possível buscar o filme no momento, tente mais tarde!");
			e.printStackTrace();
		}
	}

	@Override
	public User getEntity() {
		if (this.entity == null)
			this.entity = new User();
		
		return this.entity;
	}
	
	public String getSearch() {
		return search;
	}
	
	public void setSearch(String search) {
		this.search = search;
	}

}
