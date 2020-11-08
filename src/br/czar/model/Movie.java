package br.czar.model;

import javax.validation.constraints.NotNull;

public class Movie {
	@NotNull
	private Integer id;
	private String title;
	private String sinopse;
	private Integer duration;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
}
