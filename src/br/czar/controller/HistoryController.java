package br.czar.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.czar.dao.VendaDAO;
import br.czar.model.Sell;
import br.czar.model.User;
import br.czar.util.SessionStorage;
import br.czar.util.Utils;

@Named
@ViewScoped
public class HistoryController implements Serializable {
	private static final SessionStorage sessionStorage = SessionStorage.getInstance();
	private static final long serialVersionUID = 7985741902828194107L;

	private List<Sell> sellList;

	public List<Sell> getSellList() {
		if (sellList == null) {
			VendaDAO dao = new VendaDAO();
			Object obj = sessionStorage.getItem("userData");
			
			if (obj != null)
				try {
					sellList = dao.getAll((User)obj);
				} catch (Exception e) {
					Utils.addErrorMessage("Não foi possível obter o histórico de vendas.");
					sellList = new ArrayList<>();
				}
		}
		return sellList;
	}
	
	public void Details(Sell sell) {
		
	}

	public void setSellList(List<Sell> listaVenda) {
		this.sellList = listaVenda;
	}
	

}
