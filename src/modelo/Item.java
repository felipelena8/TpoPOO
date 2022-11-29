package modelo;

public class Item {
private int cantidad;
private double total;
private Articulo articulo;
public double calcularTotalItem() {
	total = articulo.getPrecio()*cantidad;
	return total;
}
public Articulo getArticulo() {
	return articulo;
}
public int getCantidad() {
	return cantidad;
}
public double getTotal() {
	return total;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}

public Item(Articulo articulo, int cantidad) {
	this.articulo = articulo;
	this.cantidad=cantidad;
}
}
