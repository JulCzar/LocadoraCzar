package br.czar.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
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
	private String filter = "1";
	private String query;
	
	public UserController() {
		super(new UserDAO());
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("userInfo");
		setEntity((User)flash.get("userInfo"));
	}
	
	public void search() {
		try {
			setEntityList(dao.search(getQuery(), filter));
		} catch (Exception e) {
			Utils.addErrorMessage("Não foi possível buscar o filme no momento, tente mais tarde!");
			e.printStackTrace();
		}
	}
	
	public void verifyEmail() {
		UserDAO dao = (UserDAO)this.dao;
		try {
			if (dao.getOne(getEntity().getEmail()))
				Utils.addErrorMessage("Email já cadastrado no sistema!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePassword() {
		String email = getEntity().getEmail();
		String password = getEntity().getPassword();
		String passwordB64 = Utils.base64Parse(password);
		getEntity().setPassword(Utils.hashParse(email+password+passwordB64));
		
		update();
	}
	
	@Override
	public User getEntity() {
		if (this.entity == null)
			this.entity = new User();
		
		return this.entity;
	}
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String search) {
		this.query = search;
	}
	
	public Privilege[] getPrivileges() {
		return Privilege.values();
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getInputId() {
		if (filter.equals("3"))
			return "cpfInput";
		else
			return "userInput";
	}
}
