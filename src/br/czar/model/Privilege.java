package br.czar.model;

public enum Privilege {
	ADMIN(1, "Administrador"),
	USER(2, "Usuario"),
	EMPLOYEE(3, "Funcionario");
	
	private int id;
	private String label;
	
	Privilege(int id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static Privilege valueOf(int id) {
		for (Privilege p : values())
			if (p.getId() == id)
				return p;
		
		return null;
	}
}
