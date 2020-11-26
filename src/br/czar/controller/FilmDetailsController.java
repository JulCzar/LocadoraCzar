package br.czar.controller;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.czar.dao.DAO;
import br.czar.dao.MovieDAO;
import br.czar.model.Movie;
import br.czar.util.Utils;

@Named
@ViewScoped
public class FilmDetailsController implements Serializable {
	private static final long serialVersionUID = -9167731946763516124L;
	
	DAO<Movie> dao = new MovieDAO();
	Movie movie;
	
	public void edit() {
		try {
			setMovie(dao.getOne(movie));
		} catch (Exception e) {
			e.printStackTrace();
			Utils.addErrorMessage("Não foi possível encontrar o filme no banco de dados.");
			return;
		}
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("movieInfo", getMovie());
		Utils.redirect("editFilm.xhtml");
	}

	public Movie getMovieDetails() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		movie = new Movie();

		movie.setId(id!=null?Integer.parseInt(id):0);
		
		try {
			movie = dao.getOne(movie);
		} catch (Exception e) {
			e.printStackTrace();
			movie = null;
		}
		
		return movie;
	}

	public Movie getMovie() {
		if (this.movie == null)
			setMovie(getMovieDetails());
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
