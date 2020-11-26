package br.czar.controller;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.czar.dao.DAO;
import br.czar.dao.UserDAO;
import br.czar.model.User;
import br.czar.util.Utils;

@Named
@ViewScoped
public class UserDetailsController implements Serializable {
	private static final long serialVersionUID = -2272168343133520820L;
	
	DAO<User> dao = new UserDAO();
	User user;
	
	public void edit() {
		try {
			setUser(dao.getOne(user));
		} catch (Exception e) {
			e.printStackTrace();
			Utils.addErrorMessage("Não foi possível encontrar o usuário no banco de dados.");
			return;
		}
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("userInfo", user);
		Utils.redirect("editUser.xhtml");
	}

	public User getUserDetails() {
		user = new User();
		String id = FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRequestParameterMap()
				.get("u");

		user.setId(id!=null?Integer.parseInt(id):0);
		
		try {
			user = dao.getOne(user);
		} catch (Exception e) {
			e.printStackTrace();
			user = null;
		}
		
		return user;
	}

	public User getUser() {
		if (this.user == null)
			setUser(getUserDetails());
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
