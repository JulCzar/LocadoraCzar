package br.czar.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sell {
	private Integer id;
	private LocalDateTime date;
	private User user;
	private Double total;
	private List<SellItem> movies;
	
	public Sell() {
		this.movies = new ArrayList<>();
	}
	
	public Sell(Double total) {
		this.movies = new ArrayList<>();
		this.total = total;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public List<SellItem> getMovies() {
		return movies;
	}
	public void setMovies(List<SellItem> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Sell = {\n\tid: " + id + ",\n\tdate: " + date + ",\n\tuser: " + user + ",\n\ttotal: " + total
				+ ",\n\tmovies: " + movies + "\n}";
	}
}
