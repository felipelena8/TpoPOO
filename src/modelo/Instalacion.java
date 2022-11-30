package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modelo.costos.Almuerzo;
import modelo.costos.Costo;
import modelo.enums.EstadoInstalacion;

public class Instalacion {

	private static long idCount;
	private long id;
	private EstadoInstalacion estado;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private Tecnico tecnico;
	private Cliente cliente;
	private Costo costo;
	private Almuerzo almuerzo;
	private List<Item> articulos;

	public Instalacion(LocalDateTime fechaInicio, LocalDateTime fechaFin, Cliente cliente) {
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
		this.cliente = cliente;
		estado = EstadoInstalacion.PROGRAMADA;
		id = idCount++;
		articulos = new ArrayList<Item>();
	}

	public long getId() {
		return id;
	}

	public EstadoInstalacion getEstado() {
		return estado;
	}

	public void setEstado(EstadoInstalacion estado) {
		this.estado = estado;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void calcularTiempoTrabajado(LocalDateTime fechaIni, LocalDateTime fechaFin) {
		costo.calcularTiempoTrabajado(fechaIni, fechaFin);
	}

	public void modificarFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public void finalizarInstalacion() {
		this.estado = EstadoInstalacion.FINALIZADA;
		costo.calcularTiempoTrabajado(fechaInicio, fechaFin);
	}

	public void reasignarTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public void agregarItemDetalle(Item item) {
		for (Item i : articulos) {
			if (i.getArticulo().getDescripcion().equals(item.getArticulo().getDescripcion())) {
				System.out.println(i.getCantidad());
				i.setCantidad(i.getCantidad() + item.getCantidad());
				
				return;
			}
		}
		articulos.add(item);
	}

}
