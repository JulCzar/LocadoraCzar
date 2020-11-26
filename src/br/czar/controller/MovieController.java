package br.czar.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.czar.dao.MovieDAO;
import br.czar.model.Movie;
import br.czar.model.Parental;
import br.czar.util.Utils;

@Named
@ViewScoped
public class MovieController extends Controller<Movie> implements Serializable {
	private static final long serialVersionUID = 2480025844694481727L;
	
	public MovieController() {
		super(new MovieDAO());
	}
	
	public void getMovieDetails(Movie m) {		
		try {
			setEntity(dao.getOne(m));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void registerMovie() {
		try {
			dao.insert(entity);
			
			Utils.addInfoMessage("O Filme foi adicionado com sucesso ao nosso banco de dados");
		} catch (Exception e) {
			Utils.addErrorMessage("Houve um problema para adicionar o filme, tente novamente");
			
			e.printStackTrace();
		}
	}
	
	public Parental[] getParentals() {
		return Parental.values();
	}

	@Override
	public Movie getEntity() {
		if (this.entity == null)
			this.entity = new Movie();
		
		return entity;
	}
	
}
