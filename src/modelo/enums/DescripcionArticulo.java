package modelo.enums;

public enum DescripcionArticulo {
	CONDENSADORA(20.0), EVAPORADORA(10.0), KIT_DE_INSTALACION(23.0), SOPORTE_PARED(30.0);

	private double precio;

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	DescripcionArticulo(double precio) {
		this.precio = precio;
	}

}
