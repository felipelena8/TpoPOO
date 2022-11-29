package modelo;

import modelo.enums.DescripcionArticulo;

public class Articulo {
	private int stock;
	private DescripcionArticulo descr;

	public double getPrecio() {
		return descr.getPrecio();
	}

	public void setPrecio(double precio) {
		descr.setPrecio(precio);
	}

	public int getStock() {
		return stock;
	}

	public void agregarStock(int stock) {
		this.stock+=stock;
	}
	
	public void quitarStock(int stock) {
		this.stock-=stock;
	}


	public DescripcionArticulo getDescripcion() {
		return descr;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public Articulo(DescripcionArticulo descr, int stock) {
		this.descr = descr;
		this.stock = stock;
	}
}
