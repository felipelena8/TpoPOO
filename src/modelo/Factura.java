package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import modelo.enums.DescripcionArticulo;
import modelo.enums.TipoFactura;

public class Factura {
	private Cliente cliente;
	private static final double IVA = 0.21;
	private static long countNro = 0;
	private long numero;
	private double precioTotal;
	private List<Item> itemsDetalle;
	private TipoFactura tipo;

	public double calcularTotalFactura() {
		double suma = 0;
		for (Item item : itemsDetalle) {
			suma += item.calcularTotalItem();
		}
		precioTotal = suma;
		return suma;
	}

	public boolean soyFactura(long nro) {
		return numero == nro;
	}

	public List<Item> getItemsDetalle() {
		return itemsDetalle;
	}

	public long getNumero() {
		return numero;
	}

	public void agregarItemDetalle(Item item) {
		for (Item i : itemsDetalle) {
			if (i.getArticulo().getDescripcion().equals(item.getArticulo().getDescripcion())) {
				i.setCantidad(i.getCantidad() + item.getCantidad());
				
				return;
			}
		}
		itemsDetalle.add(item);
	}
	
	public void setItemDetalle(DescripcionArticulo descripcion, int cantidad) {
		for (Item i : itemsDetalle) {
			if (i.getArticulo().getDescripcion().equals(descripcion)) {
				i.setCantidad(cantidad);
				return;
			}
		}
		itemsDetalle.add(new Item(new Articulo(descripcion, 0),cantidad));
		calcularTotalFactura();
	}

	public Factura(TipoFactura tipo, Cliente cliente) {
		numero = countNro++;
		this.tipo = tipo;
		this.cliente = cliente;
		itemsDetalle= new ArrayList<Item>();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public TipoFactura getTipo() {
		return tipo;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public DefaultTableModel informacionItems() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers("Descripcion-Cantidad-Valor unitario-Valor total".split("-"));
		for(Item i: itemsDetalle) {
			modelo.addRow(new Object[] {i.getArticulo().getDescripcion(), i.getCantidad(), i.getArticulo().getPrecio(), i.calcularTotalItem()});
		}
		
		return modelo;
	}

	public static double getIva() {
		return IVA;
	}
}
