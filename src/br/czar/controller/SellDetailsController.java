package br.czar.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.czar.dao.DAO;
import br.czar.dao.VendaDAO;
import br.czar.model.Sell;

@Named
@ViewScoped
public class SellDetailsController implements Serializable {
	private static final long serialVersionUID = -2315910214109378963L;
	DAO<Sell> dao = new VendaDAO();
	private Sell sell;

	public Sell getSellDetails() {
		this.sell = new Sell(); 
		String id = FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRequestParameterMap().get("id");

		sell.setId(id!=null?Integer.parseInt(id):0);
		
		try {
			sell = dao.getOne(sell);
		} catch (Exception e) {
			e.printStackTrace();
			sell = null;
		}
		
		return sell;
	}
}
