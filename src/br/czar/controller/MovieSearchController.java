package br.czar.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.czar.dao.MovieDAO;
import br.czar.model.Movie;

@Named
@ViewScoped
public class MovieSearchController implements Serializable {
	private static final long serialVersionUID = -8172307758698826296L;

	MovieDAO dao;
	private String filter = "1";
	private String query = "";
	private List<Movie> movieList;
	
	public MovieSearchController() {
		this.dao = new MovieDAO();
	}
	
	public void search() {
		try {
			setMovieList(dao.search(query, filter));
		} catch (Exception e) {
			e.printStackTrace();
			setMovieList(null);
		}
	}
	
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String search) {
		this.query = search;
	}
	public List<Movie> getMovieList() {
		if (this.movieList == null) try {
			setMovieList(dao.getAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movieList;
	}
	public void setMovieList(List<Movie> listaMidia) {
		this.movieList = listaMidia;
	}
	
	public String getInputType() {
		if (filter.equals("2"))
			return "number";
		
		return "text";
	}
	
}
