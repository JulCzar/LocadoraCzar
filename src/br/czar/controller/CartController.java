package br.czar.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.czar.dao.VendaDAO;
import br.czar.model.Cart;
import br.czar.model.Sell;
import br.czar.model.SellItem;
import br.czar.model.User;
import br.czar.util.SessionStorage;
import br.czar.util.Utils;

@Named
@RequestScoped
public class CartController {
	private static final SessionStorage sessionStorage = SessionStorage.getInstance();

	private Sell sell;
	
	public Cart getCart() {
		Object obj = sessionStorage.getItem("cart");
		
		return (obj == null)?new Cart():(Cart)obj;
	}
	public Double getTotalPrice() {
		Double price = 0.0;
		
		for (SellItem i : getCart().getItems())
			price += i.getPrice() * i.getQuantity();
		
		return price;
	}
	
	public void completeSell() {
		Object obj = sessionStorage.getItem("userData");
		if (obj == null) {
			Utils.addErrorMessage("Para finalizar a compra o usuário deve estar logado.");
			return;
		}
		
		getSell().setUser((User)obj);
		
		VendaDAO dao = new VendaDAO();
		try {
			dao.insert(getSell());
			Utils.addInfoMessage("Venda realizada com sucesso.");
			
			sessionStorage.setItem("cart", null);
			setSell(null);
			
		} catch (Exception e) {
			Utils.addErrorMessage("Não é possivel fazer uma inclusão.");
			e.printStackTrace();
		}
	}
	
	public Sell getSell() {
		if (sell == null) 
			sell = new Sell(getTotalPrice());
		
		Object obj = sessionStorage.getItem("cart");
		if (obj != null)
			sell.setMovies(((Cart)obj).getItems());
		
		return sell;
	}
	
	public void setSell(Sell sell) {
		this.sell = sell;
	}
	
	
}
