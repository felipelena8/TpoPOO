package modelo;

public enum Turno {
	
	
	
	MANANA(6, 13),
	TARDE(13, 20),
	AMBOS(6, 20);

	private Integer inicio;
	private Integer fin;
	
	Turno(Integer inicio, Integer fin) {
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public Integer getInicio() {
		return inicio;
	}

	public Integer getFin() {
		return fin;
	}
	
}
