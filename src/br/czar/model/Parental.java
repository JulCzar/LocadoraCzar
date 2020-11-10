package br.czar.model;

public enum Parental {
	FREE(1, "Livre"),
	R10(2, "10 anos"),
	R12(3, "12 anos"),
	R14(4, "14 anos"),
	R16(5, "16 anos"),
	R18(6, "18 anos");
	
	private int id;
	private String label;
	
	Parental(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}

	public static Parental valueOf(int id) {
		for (Parental p : values())
			if (p.getId() == id)
				return p;
		
		return null;
	}
	
}
