package modelo;

public class Item {
private int cantidad;
private double total;
private Articulo articulo;
public double calcularTotalItem() {
	total = articulo.getPrecio()*cantidad;
	return total;
}
}
