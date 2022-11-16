package modelo;

import enums.DescripcionArticulo;

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
	this.stock = stock;
}

}
