package br.czar.model;

import java.time.LocalDate;
import java.util.List;

public class Sell {
	private LocalDate date;
	private User user;
	private List<SellItem> movies;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<SellItem> getMovies() {
		return movies;
	}
	public void setMovies(List<SellItem> movies) {
		this.movies = movies;
	}
	
	@Override
	public String toString() {
		return "Sell = {\n\tdate: " + date + ",\n\tuser: " + user + ",\n\tmovies: " + movies + "\n}";
	}
}
