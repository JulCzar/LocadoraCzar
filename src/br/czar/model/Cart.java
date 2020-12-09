package br.czar.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<SellItem> items;

	public Cart() {
		this.items = new ArrayList<>();
	}
	public void addToCart(SellItem item) {
		int itemPos = items.indexOf(item);
		
		if (itemPos == -1) 
			items.add(item);
		else
			items.get(itemPos).increaseQuantity();
	}
	public List<SellItem> getItems() {
		return items;
	}
	public void setItems(List<SellItem> items) {
		this.items = items;
	}
	
	public void remove(SellItem s) {
		items.remove(s);
	}
	@Override
	public String toString() {
		return "Cart = {\n\titems: " + items + "\n}";
	}
	
}
