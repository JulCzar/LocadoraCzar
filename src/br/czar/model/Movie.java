package br.czar.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

public class Movie {
	private Integer id;
	@NotBlank(message = "O Filme precisa possuir um título.")
	private String title;
	@NotBlank(message = "O Filme precisa possuir sinópse.")
	private String sinopse;
	private LocalDate release;
	private String image;
	private Double price;
	private Integer stock;
	private Parental rate;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public LocalDate getRelease() {
		return release;
	}
	public void setRelease(LocalDate release) {
		this.release = release;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Parental getRate() {
		return rate;
	}
	public void setRate(Parental rate) {
		this.rate = rate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Movie = {\n\tid: " + id + ",\n\ttitle: " + title + ",\n\tsinopse: " + sinopse + ",\n\trelease: "
				+ release + ",\n\timage: " + image + ",\n\tprice: " + price + ",\n\tstock: " + stock + ",\n\trate: "
				+ rate + "\n}";
	}	
}
