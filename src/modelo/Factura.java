package modelo;
import java.util.Iterator;
import java.util.List;

import enums.TipoFactura;

public class Factura {
private Cliente cliente;
private static final double IVA =0.21;
private static long countNro = 0;
private long numero;
private double precioTotal;
private List<Item> itemsDetalle;
private TipoFactura tipo;

public double calcularTotalFactura() {
	double suma = 0;
	for(Item item:itemsDetalle) {
		suma+= item.calcularTotalItem();
	}
	precioTotal=suma;
	return suma;
}
public boolean soyFactura(long nro) {
	return numero == nro;
}

public Factura(TipoFactura tipo) {
	this.tipo = tipo;
}
}
