package br.czar.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.czar.model.Cart;
import br.czar.model.SellItem;
import br.czar.util.SessionStorage;

@Named
@ViewScoped
public class CartController implements Serializable {
	private static final SessionStorage sessionStorage = SessionStorage.getInstance();
	private static final long serialVersionUID = -7848983003715086991L;
	private Cart cart;
	
	public Cart getCart() {
		if (cart == null) {
			Object obj = sessionStorage.getItem("cart");
			cart = (obj == null)?new Cart():(Cart)obj;
		}
		
		return cart;
	}
	public Double getTotalPrice() {
		Double price = 0.0;
		
		for (SellItem i : getCart().getItems())
			price += i.getPrice() * i.getQuantity();
		
		return price;
	}
}
