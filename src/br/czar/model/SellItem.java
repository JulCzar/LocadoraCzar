package br.czar.model;

public class SellItem {
	private Integer id;
	private Movie movie;
	private Integer quantity;
	private Double price;
	
	public SellItem() {}
	
	public SellItem(Movie movie) {
		this.movie = movie;
		this.price = movie.getPrice();
		this.quantity = 1;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public void increaseQuantity() {
		if (quantity + 1 <= movie.getStock())
			quantity += 1;
	}
	public void decreaseQuantity() {			
		if (quantity - 1 > 0)
			quantity -= 1;
	}
	public Double getPrice() {
		return price;
	}
	public Double getTotalPrice() {
		return getPrice() * quantity;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movie == null) ? 0 : movie.hashCode());
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
		SellItem other = (SellItem) obj;
		if (movie == null) {
			if (other.movie != null)
				return false;
		} else if (!movie.equals(other.movie))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SellItem = {\n\tid: " + id + ",\n\tmovie: " + movie + ",\n\tquantity: " + quantity + ",\n\tprice: "
				+ price + "\n}";
	}
}
